package br.edu.ifpb.restdelivery.enumerations;

public enum PathFile {
	RESTDELIVERY("restDelivery");
	
	private String name;
	
	private PathFile(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
