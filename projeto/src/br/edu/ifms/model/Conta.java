package br.edu.ifms.model;

import java.util.List;

public class Conta {
	private long id;
	private int num;
	private float saldo;
 
 private Cliente cliente;
 private List<ContaInvestimento> investimentos;
 
 public void sacar() {}
 
 public void depositar() {}
 
 public Conta() {}

 
public Conta(int num, float saldo) {
	super();
	this.num = num;
	this.saldo = saldo;
}

public Conta(long id, int num, float saldo) {
	super();
	this.id = id;
	this.num = num;
	this.saldo = saldo;
	
}

public long getId() {
	return id;
}

public void setId(long id) {
	this.id = id;
}

public int getNum() {
	return num;
}

public void setNum(int num) {
	this.num = num;
}

public float getSaldo() {
	return saldo;
}

public void setSaldo(float saldo) {
	this.saldo = saldo;
}
 

 


 
 
}
