package br.edu.ifms.dao;

import br.edu.ifms.model.Conta;
import br.edu.ifms.model.ContaInvestimento;
import br.edu.ifms.model.Investimento;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

public class ContaInvestimentoDAOImpl extends DAOImpl<ContaInvestimento>
        implements ContaInvestimentoDAO {

    @Override protected String getTabela() { return "conta_investimento"; }

    @Override protected String getSqlInserir() {
        return "INSERT INTO conta_investimento (data, valor, id_conta, id_investimento) "
             + "VALUES (?, ?, ?, ?)";
    }

    @Override protected String getSqlAtualizar() {
        return "UPDATE conta_investimento SET data = ?, valor = ?, id_conta = ?, "
             + "id_investimento = ? WHERE id = ?";
    }

    @Override protected void preencherInsert(PreparedStatement ps, ContaInvestimento ci) throws SQLException {
        ps.setObject(1, ci.getData(), Types.DATE);
        ps.setFloat(2, ci.getValor());
        ps.setLong(3, ci.getConta().getId());
        ps.setLong(4, ci.getInvestimento().getId());
    }

    @Override protected void preencherUpdate(PreparedStatement ps, ContaInvestimento ci) throws SQLException {
        preencherInsert(ps, ci);
        ps.setLong(5, ci.getId());
    }

    @Override protected ContaInvestimento mapear(ResultSet rs) throws SQLException {
        ContaInvestimento ci = new ContaInvestimento(
                rs.getLong("id"),
                rs.getObject("data", LocalDate.class),
                rs.getFloat("valor"));

        Conta conta = new Conta();                 // só o id
        conta.setId(rs.getLong("id_conta"));
        ci.setConta(conta);

        Investimento inv = new Investimento();     // só o id
        inv.setId(rs.getLong("id_investimento"));
        ci.setInvestimento(inv);
        return ci;
    }

    @Override protected void setId(ContaInvestimento ci, long id) { ci.setId(id); }

    @Override
    public List<ContaInvestimento> listarPorConta(long idConta) {
        return consultar("SELECT * FROM conta_investimento WHERE id_conta = ? ORDER BY id", idConta);
    }

    @Override
    public List<ContaInvestimento> listarPorInvestimento(long idInvestimento) {
        return consultar("SELECT * FROM conta_investimento WHERE id_investimento = ? ORDER BY id",
                         idInvestimento);
    }
}