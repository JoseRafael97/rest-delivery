package br.edu.ifpb.restdelivery.enumerations;

public enum CategoryType {
	DESSERTS("Sobremesas","sobremesas.png"),
	SNACKS("Lanches", "lanches.png"),
	PIZZA("Pizzas", "pizzas.png"),
	SALTED("Salgados", "salgados.png"),
	DRINKS("Bebidas", "bebidas.png"),
	SALADS("Saladas", "saladas.png"),
	LUNCHES("Almoços", "almocos.png"),
	COMBINED("Combinados", "combinados.png");

	
	String nome;
	String pathfile;
	
	private CategoryType(String atributo,String pathfile) {
		this.nome = atributo;
		this.pathfile = pathfile;
		
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPathfile() {
		return pathfile;
	}

	public void setPathfile(String pathfile) {
		this.pathfile = pathfile;
	}
	
	
}
