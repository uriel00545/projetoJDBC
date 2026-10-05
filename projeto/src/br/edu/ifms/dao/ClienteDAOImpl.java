package br.edu.ifms.dao;

import br.edu.ifms.model.Cliente;
import br.edu.ifms.model.Endereco;

import java.sql.*;
import java.util.List;

public class ClienteDAOImpl extends DAOImpl<Cliente> implements ClienteDAO {

    private final EnderecoDAO enderecoDAO = new EnderecoDAOImpl();

    @Override protected String getTabela() { return "cliente"; }

    @Override protected String getSqlInserir() {
        return "INSERT INTO cliente (nome, cpf, id_endereco) VALUES (?, ?, ?)";
    }

    @Override protected String getSqlAtualizar() {
        return "UPDATE cliente SET nome = ?, cpf = ?, id_endereco = ? WHERE id = ?";
    }

    @Override protected void preencherInsert(PreparedStatement ps, Cliente c) throws SQLException {
        ps.setString(1, c.getNome());
        ps.setString(2, c.getCpf());
        if (c.getEndereco() != null) {
            ps.setLong(3, c.getEndereco().getId());
        } else {
            ps.setNull(3, Types.BIGINT);
        }
    }

    @Override protected void preencherUpdate(PreparedStatement ps, Cliente c) throws SQLException {
        preencherInsert(ps, c);
        ps.setLong(4, c.getId());
    }

    @Override protected Cliente mapear(ResultSet rs) throws SQLException {
        Cliente c = new Cliente(rs.getLong("id"), rs.getString("nome"), rs.getString("cpf"));
        long idEndereco = rs.getLong("id_endereco");
        if (!rs.wasNull()) {
            c.setEndereco(enderecoDAO.buscarPorId(idEndereco));
        }
        return c;
    }

    @Override protected void setId(Cliente c, long id) { c.setId(id); }

 
    @Override
    public void inserir(Cliente c) {
        salvarEndereco(c.getEndereco());
        super.inserir(c);
    }

    @Override
    public void atualizar(Cliente c) {
        salvarEndereco(c.getEndereco());
        super.atualizar(c);
    }

    @Override
    public void deletar(long id) {
        Cliente c = buscarPorId(id);
        super.deletar(id);   
        if (c != null && c.getEndereco() != null) {
            enderecoDAO.deletar(c.getEndereco().getId());
        }
    }

    @Override
    public Cliente buscarPorId(long id) {
        return completar(super.buscarPorId(id));
    }

    @Override
    public Cliente buscarPorCpf(String cpf) {
        List<Cliente> lista = consultar("SELECT * FROM cliente WHERE cpf = ?", cpf);
        return lista.isEmpty() ? null : completar(lista.get(0));
    }

    private void salvarEndereco(Endereco e) {
        if (e == null) return;
        if (e.getId() == 0) {
            enderecoDAO.inserir(e);
        } else {
            enderecoDAO.atualizar(e);
        }
    }

    
    private Cliente completar(Cliente c) {
        if (c != null) {
            c.setContas(new ContaDAOImpl().listarPorCliente(c.getId()));
        }
        return c;
    }
}