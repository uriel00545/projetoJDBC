package br.edu.ifms.model;

public class Endereco {
  private long id;
  private String rua;
  private String cidade;
  private String cep;
  
  public void atualizarEndereco() {
	  
  }
  
  public String exibirEndereco() {
	  return toString();  
  }
  @Override
  public String toString() {
      return "rua:\n " + rua + " - " + cidade + " CEP:\n " + cep;     
  }
  
  	public Endereco(){
			super();
		}
  	
    public Endereco(long id, String rua, String cidade, String cep) {
    	super();
    	this.id = id;
    	this.rua = rua;
    	this.cidade = cidade;
    	this.cep = cep;
}
	
	public Endereco(String rua, String cidade, String cep) {
		super();
		this.rua = rua;
		this.cidade = cidade;
		this.cep = cep;
	}
			
public long getId() {
	return id;
		}
public void setId(long id) {
			this.id = id;
		}

public String getRua() {
	return rua;
	
}
public void setRua(String rua) {
	this.rua = rua;
}
public String getCidade() {
	return cidade;
}
public void setCidade(String cidade) {
	this.cidade = cidade;
}
public String getCep() {
	return cep;
}
public void setCep(String cep) {
	this.cep = cep;
}
  
  
  
}
