package br.edu.ifms.dao;

import br.edu.ifms.model.Endereco;

import java.sql.*;

public class EnderecoDAOImpl extends DAOImpl<Endereco> implements EnderecoDAO {

    @Override protected String getTabela() { return "endereco"; }

    @Override protected String getSqlInserir() {
        return "INSERT INTO endereco (rua, cidade, cep) VALUES (?, ?, ?)";
    }

    @Override protected String getSqlAtualizar() {
        return "UPDATE endereco SET rua = ?, cidade = ?, cep = ? WHERE id = ?";
    }

    @Override protected void preencherInsert(PreparedStatement ps, Endereco e) throws SQLException {
        ps.setString(1, e.getRua());
        ps.setString(2, e.getCidade());
        ps.setString(3, e.getCep());
    }

    @Override protected void preencherUpdate(PreparedStatement ps, Endereco e) throws SQLException {
        preencherInsert(ps, e);
        ps.setLong(4, e.getId());
    }

    @Override protected Endereco mapear(ResultSet rs) throws SQLException {
        return new Endereco(rs.getLong("id"), rs.getString("rua"),
                            rs.getString("cidade"), rs.getString("cep"));
    }

    @Override protected void setId(Endereco e, long id) { e.setId(id); }
}