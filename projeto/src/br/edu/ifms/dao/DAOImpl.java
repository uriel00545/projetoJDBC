package br.edu.ifms.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class DAOImpl<T> implements DAO<T> {

   
    protected abstract String getTabela();
    protected abstract String getSqlInserir();
    protected abstract String getSqlAtualizar();
  
    protected abstract void preencherInsert(PreparedStatement ps, T entidade) throws SQLException;
    protected abstract void preencherUpdate(PreparedStatement ps, T entidade) throws SQLException;
    protected abstract T mapear(ResultSet rs) throws SQLException;
    protected abstract void setId(T entidade, long id);

    @Override
    public void inserir(T entidade) {
        try (Connection con = Conexao.obterConexao();
             PreparedStatement ps = con.prepareStatement(getSqlInserir(), new String[] {"id"})) {
            preencherInsert(ps, entidade);
            ps.executeUpdate();
            try (ResultSet chaves = ps.getGeneratedKeys()) {
                if (chaves.next()) {
                    setId(entidade, chaves.getLong(1));  
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir em " + getTabela(), e);
        }
    }

    @Override
    public void atualizar(T entidade) {
        try (Connection con = Conexao.obterConexao();
             PreparedStatement ps = con.prepareStatement(getSqlAtualizar())) {
            preencherUpdate(ps, entidade);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar " + getTabela(), e);
        }
    }

    @Override
    public void deletar(long id) {
        String sql = "DELETE FROM " + getTabela() + " WHERE id = ?";
        try (Connection con = Conexao.obterConexao();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar de " + getTabela(), e);
        }
    }

    @Override
    public T buscarPorId(long id) {
        List<T> lista = consultar("SELECT * FROM " + getTabela() + " WHERE id = ?", id);
        return lista.isEmpty() ? null : lista.get(0);
    }

    @Override
    public List<T> listarTodos() {
        return consultar("SELECT * FROM " + getTabela() + " ORDER BY id");
    }

    
    protected List<T> consultar(String sql, Object... parametros) {
        List<T> lista = new ArrayList<>();
        try (Connection con = Conexao.obterConexao();
             PreparedStatement ps = con.prepareStatement(sql)) {
            for (int i = 0; i < parametros.length; i++) {
                ps.setObject(i + 1, parametros[i]);
            }
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapear(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao consultar " + getTabela(), e);
        }
        return lista;
    }
}