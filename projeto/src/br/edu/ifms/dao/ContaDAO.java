package br.edu.ifms.dao;

import br.edu.ifms.model.Conta;

import java.util.List;

public interface ContaDAO extends DAO<Conta> {
    List<Conta> listarPorCliente(long idCliente);
    Conta buscarPorNum(int num);
}