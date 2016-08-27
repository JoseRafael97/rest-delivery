package br.edu.ifpb.restdelivery.enumerations;

public enum StateNames {
	
	ACRE("Acre",1),
	ALAGOAS("Alagoas",2),
	AMAZONAS("Amazonas",3),
	AMAPÁ("Amapá",4),
	BAHIA("Bahia",5),
	CEARA("Ceará",6),
	DF("Distrito Federal",7),
	ESPIRITOSANTO("Espírito Santo",8),
	GOIAS("Goiás",9),
	MARANHAO("Maranhão",10),
	MG("Minas Gerais",11),
	MTDOSUL("Mato Grosso do Sul",12),
	MT("Mato Grosso",13),
	PA("Pará",14),
	PB("Paraíba",15),
	PE("Pernambuco",16),
	PI("Piauí",17),
	PR("Paraná",18),
	RJ("Rio de Janeiro",19),
	RN("Rio Grande do Norte",20),
	RO("Rondônia",21),
	RR("Roraima",22),
	RS("Rio Grande do Sul",23),
	SC("Santa Catarina",24),
	SE("Sergipe",25),
	SP("São Paulo",26),
	TO("Tocantins",27);
	
	String nome;
	int id;
	
	private StateNames (String atributo, int valor) {
		this.nome = atributo;	
		this.id = valor;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	

}
