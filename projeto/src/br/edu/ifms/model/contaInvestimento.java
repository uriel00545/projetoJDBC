package br.edu.ifms.model;

import java.time.LocalDate;

public class contaInvestimento {
   private long id;
   private LocalDate data;
   private float valor; 
 
   public contaInvestimento() {}

   public contaInvestimento(long id, LocalDate data, float valor) {
	super();
	this.id = id;
	this.data = data;
	this.valor = valor;
	
   }

	public contaInvestimento(LocalDate data, float valor) {
	super();
	this.data = data;
	this.valor = valor;
	
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}
   
   
   
}
