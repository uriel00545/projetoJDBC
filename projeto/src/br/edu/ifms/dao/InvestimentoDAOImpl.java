package br.edu.ifms.dao;

import br.edu.ifms.model.CDB;
import br.edu.ifms.model.Investimento;
import br.edu.ifms.model.Tesouro;

import java.sql.*;
import java.time.LocalDate;

public class InvestimentoDAOImpl extends DAOImpl<Investimento> implements InvestimentoDAO {

    @Override protected String getTabela() { return "investimento"; }

    @Override protected String getSqlInserir() {
        return "INSERT INTO investimento (tipo, valor, rentabilidade, taxa, vencimento) "
             + "VALUES (?, ?, ?, ?, ?)";
    }

    @Override protected String getSqlAtualizar() {
        return "UPDATE investimento SET tipo = ?, valor = ?, rentabilidade = ?, taxa = ?, "
             + "vencimento = ? WHERE id = ?";
    }

    @Override protected void preencherInsert(PreparedStatement ps, Investimento i) throws SQLException {
        String tipo = "INVESTIMENTO";
        Double taxa = null;
        LocalDate vencimento = null;

        if (i instanceof CDB cdb) {
            tipo = "CDB";
            taxa = cdb.getTaxa();
        } else if (i instanceof Tesouro t) {
            tipo = "TESOURO";
            vencimento = t.getVencimento();
        }

        ps.setString(1, tipo);
        ps.setFloat(2, i.getValor());
        ps.setDouble(3, i.getRentabilidade());
        ps.setObject(4, taxa, Types.NUMERIC);
        ps.setObject(5, vencimento, Types.DATE);
    }

    @Override protected void preencherUpdate(PreparedStatement ps, Investimento i) throws SQLException {
        preencherInsert(ps, i);
        ps.setLong(6, i.getId());
    }

    @Override protected Investimento mapear(ResultSet rs) throws SQLException {
        Investimento i;
        switch (rs.getString("tipo")) {
            case "CDB" -> {
                CDB cdb = new CDB();
                cdb.setTaxa(rs.getDouble("taxa"));
                i = cdb;
            }
            case "TESOURO" -> {
                Tesouro t = new Tesouro();
                t.setVencimento(rs.getObject("vencimento", LocalDate.class));
                i = t;
            }
            default -> i = new Investimento();
        }
        i.setId(rs.getLong("id"));
        i.setValor(rs.getFloat("valor"));
        i.setRentabilidade(rs.getDouble("rentabilidade"));
        return i;
    }

    @Override protected void setId(Investimento i, long id) { i.setId(id); }
}