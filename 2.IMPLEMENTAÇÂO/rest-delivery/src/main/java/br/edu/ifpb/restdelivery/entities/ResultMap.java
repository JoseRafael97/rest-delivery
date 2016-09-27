package br.edu.ifpb.restdelivery.entities;

public class ResultMap {
	
	private String name;
	private Long value;
	
	public ResultMap(String name, Long value) {
		this.name = name;
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getValue() {
		return value;
	}
	public void setValue(Long value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "ResultMap [name=" + name + ", value=" + value + "]";
	}
	
	
	
	
	
}
