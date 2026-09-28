package br.edu.ifms.model;

/*
 * create table investimento(
 	id bigserial primary key,
	valor float,
	rentabilidade numeric(10,2)
);
 */

public class Investimento {

	private int id;
	private float valor;
	private double rentabilidade;
	
	public void  investir() {}
	public void resgatr() {}
	
	public Investimento() {}
	public Investimento(int id, float valor, double rentabilidade) {
		super();
		this.id = id;
		this.valor = valor;
		this.rentabilidade = rentabilidade;
	}
	public Investimento(float valor, double rentabilidade) {
		super();
		this.valor = valor;
		this.rentabilidade = rentabilidade;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}
	public double getRentabilidade() {
		return rentabilidade;
	}
	public void setRentabilidade(double rentabilidade) {
		this.rentabilidade = rentabilidade;
	}
	
	
}
