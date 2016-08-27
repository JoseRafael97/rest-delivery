package br.edu.ifpb.restdelivery.enumerations;

public enum OptionFind {
	

	RECAD("Recentes adicionados"),
	MAIOP("Maiores preços"),
	MENP("Menores preços"),
	MAISV("Mais vendidos");

	
	String nome;
	
	private OptionFind(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
