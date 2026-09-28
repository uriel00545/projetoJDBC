package br.edu.ifms.model;

import java.time.LocalDate;

public class Tesouro extends Investimento{
	private LocalDate vencimento;
	
	public Tesouro() {}

	public Tesouro(float valor, double rentabilidade, LocalDate vencimento) {
		super(valor, rentabilidade);
		this.vencimento = vencimento;
		// TODO Auto-generated constructor stub
	}

	public Tesouro(int id, float valor, double rentabilidade, LocalDate vencimento) {
		super(id, valor, rentabilidade);
		this.vencimento = vencimento;
		// TODO Auto-generated constructor stub
	}
	
	
}
