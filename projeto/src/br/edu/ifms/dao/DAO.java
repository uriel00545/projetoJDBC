package br.edu.ifms.dao;

import java.util.List;

public interface DAO<T> {
    void inserir(T entidade);
    void atualizar(T entidade);
    void deletar(long id);
    T buscarPorId(long id);
    List<T> listarTodos();
}