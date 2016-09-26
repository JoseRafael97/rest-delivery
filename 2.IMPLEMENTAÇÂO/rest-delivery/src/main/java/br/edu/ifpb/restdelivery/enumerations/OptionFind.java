package br.edu.ifpb.restdelivery.enumerations;

public enum OptionFind {
	

	RECAD("Recentes adicionados"),
	MAISV("Mais vendidos"),
	RATING("Melhores Avaliados");

	
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
