package br.edu.ifpb.restdelivery.enumerations;

public enum PaymentType {
	CREDIT("Credito"),
	DEBIT("Débito");

	
	String nome;
	
	private PaymentType (String atributo) {
		this.nome = atributo;	
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
