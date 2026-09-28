package br.edu.ifms.model;

import java.util.List;

public class Cliente {
	private int id;
	private String nome;
	private String cpf;
   
	
   private List<Conta> contas;
   private Endereco endereco;
   
   public void  cadastrar() {}
   
   public void atualizar() {}
   
   
   
public Cliente() {
	super();
	// TODO Auto-generated constructor stub
}

public Cliente(String nome, String cpf) {
	super();
	this.nome = nome;
	this.cpf = cpf;
}

public Cliente(int id, String nome, String cpf) {
	super();
	this.id = id;
	this.nome = nome;
	this.cpf = cpf;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getNome() {
	return nome;
}
public void setNome(String nome) {
	this.nome = nome;
}
public String getCpf() {
	return cpf;
}
public void setCpf(String cpf) {
	this.cpf = cpf;
}
   
}
