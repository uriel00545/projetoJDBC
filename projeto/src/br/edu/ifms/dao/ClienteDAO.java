package br.edu.ifms.dao;

import br.edu.ifms.model.Cliente;

public interface ClienteDAO extends DAO<Cliente> {
    Cliente buscarPorCpf(String cpf);
}