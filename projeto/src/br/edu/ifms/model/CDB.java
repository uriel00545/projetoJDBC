package br.edu.ifms.model;

public class CDB extends Investimento {
	private double taxa;
	
	public void calcularRendimento(){
		
	}
	
   public CDB(){}

   public CDB(int id, float valor, double rentabilidade, double taxa) {
	super(id, valor, rentabilidade);
	this.taxa = taxa;
   }

public CDB(float valor, double rentabilidade, double taxa) {
	super(valor, rentabilidade);
	this.taxa = taxa;
}

public double getTaxa() {
	return taxa;
}

public void setTaxa(double taxa) {
	this.taxa = taxa;
}
   
   

   


   
}
