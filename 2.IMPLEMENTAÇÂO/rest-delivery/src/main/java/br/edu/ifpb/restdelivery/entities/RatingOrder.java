package br.edu.ifpb.restdelivery.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class RatingOrder implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer number;
	private String comments;
	
	public Integer getNumber() {
		return number;
	}
	
	public String getComments() {
		return comments;
	}
	
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	public void setNumber(Integer number) {
		this.number = number;
	}
	
	@Override
	public String toString() {
		return "RatingOrder [number=" + number + ", comments=" + comments + "]";
	}
	
	
	
}
