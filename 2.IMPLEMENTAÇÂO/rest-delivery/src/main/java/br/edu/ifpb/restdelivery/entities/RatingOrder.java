package br.edu.ifpb.restdelivery.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * 
 * Classe que representa uma avalição realizada a um pedido feito por um cliente.
 * 
 * @author rafaelfeitosa - <a href="https://github.com/JoseRafael97 ></a>
 *
 */
@Embeddable
public class RatingOrder implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer number;
	private String comments;
	private Date date;
	
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

	@Column(name = "rating_date")
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
	
}
