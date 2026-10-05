package br.edu.ifms.dao;

import br.edu.ifms.model.ContaInvestimento;

import java.util.List;

public interface ContaInvestimentoDAO extends DAO<ContaInvestimento> {
    List<ContaInvestimento> listarPorConta(long idConta);
    List<ContaInvestimento> listarPorInvestimento(long idInvestimento);
}