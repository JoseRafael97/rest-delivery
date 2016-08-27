package br.edu.ifpb.restdelivery.enumerations;

public enum Grupo {

	ADMIN("ADMIN"), CLIENTE("CLIENTE"), ENTREGADOR("ENTREGADOR"), OPERADOR("OPERADOR");

	private String valor;

	private Grupo(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
	
	

}
