package br.edu.ifpb.restdelivery.enumerations;

public enum TypesCartCreditFlag {
	
	VISA("VISA"),
	MASTERCARD("MASTERCARD"),
	HIPER("HIPER");

	private String flagName;
	
	private TypesCartCreditFlag(String flagName) {
		this.flagName = flagName;
	}

	public String getFlagName() {
		return flagName;
	}

	public void setFlagName(String flagName) {
		this.flagName = flagName;
	}
	
	
}
