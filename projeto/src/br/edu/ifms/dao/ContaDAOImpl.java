package br.edu.ifms.dao;

import br.edu.ifms.model.*;

import java.sql.*;
import java.util.List;

public class ContaDAOImpl extends DAOImpl<Conta> implements ContaDAO {

    @Override protected String getTabela() { return "conta"; }

    @Override protected String getSqlInserir() {
        return "INSERT INTO conta (num, saldo, tipo, taxa_rendimento, limite_cheque, id_cliente) "
             + "VALUES (?, ?, ?, ?, ?, ?)";
    }

    @Override protected String getSqlAtualizar() {
        return "UPDATE conta SET num = ?, saldo = ?, tipo = ?, taxa_rendimento = ?, "
             + "limite_cheque = ?, id_cliente = ? WHERE id = ?";
    }

    @Override protected void preencherInsert(PreparedStatement ps, Conta c) throws SQLException {
        String tipo = "CONTA";
        Float taxa = null;
        Float limite = null;

        if (c instanceof ContaPoupanca p) {
            tipo = "POUPANCA";
            taxa = p.getTaxaRendimento();
        } else if (c instanceof ContaCorrente cc) {
            tipo = "CORRENTE";
            limite = cc.getLimiteCheque();
        }

        ps.setInt(1, c.getNum());
        ps.setFloat(2, c.getSaldo());
        ps.setString(3, tipo);
        ps.setObject(4, taxa, Types.NUMERIC);     
        ps.setObject(5, limite, Types.NUMERIC);
        ps.setLong(6, c.getCliente().getId());
    }

    @Override protected void preencherUpdate(PreparedStatement ps, Conta c) throws SQLException {
        preencherInsert(ps, c);
        ps.setLong(7, c.getId());
    }

    @Override protected Conta mapear(ResultSet rs) throws SQLException {
        Conta c;
        switch (rs.getString("tipo")) {
            case "POUPANCA" -> {
                ContaPoupanca p = new ContaPoupanca();
                p.setTaxaRendimento(rs.getFloat("taxa_rendimento"));
                c = p;
            }
            case "CORRENTE" -> {
                ContaCorrente cc = new ContaCorrente();
                cc.setLimiteCheque(rs.getFloat("limite_cheque"));
                c = cc;
            }
            default -> c = new Conta();
        }
        c.setId(rs.getLong("id"));
        c.setNum(rs.getInt("num"));
        c.setSaldo(rs.getFloat("saldo"));

        Cliente dono = new Cliente();         
        dono.setId(rs.getLong("id_cliente"));
        c.setCliente(dono);
        return c;
    }

    @Override protected void setId(Conta c, long id) { c.setId(id); }

    @Override
    public Conta buscarPorId(long id) {
        Conta c = super.buscarPorId(id);
        if (c != null) {
            c.setInvestimentos(new ContaInvestimentoDAOImpl().listarPorConta(c.getId()));
        }
        return c;
    }

    @Override
    public List<Conta> listarPorCliente(long idCliente) {
        return consultar("SELECT * FROM conta WHERE id_cliente = ? ORDER BY id", idCliente);
    }

    @Override
    public Conta buscarPorNum(int num) {
        List<Conta> lista = consultar("SELECT * FROM conta WHERE num = ?", num);
        return lista.isEmpty() ? null : lista.get(0);
    }
}