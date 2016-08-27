package br.edu.ifpb.restdelivery.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "rating_product")
@NamedQueries({@NamedQuery( name = "ratingProduct.averageProductRating", query = "SELECT AVG(r.number) FROM RatingProduct r JOIN r.product p WHERE p.name LIKE :nameProduct"),
	@NamedQuery(name = "ratingProductToCategory", query = "SELECT p.category, AVG(r.number) FROM RatingProduct r JOIN r.product p group by p.category")})
public class RatingProduct extends SuperId {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Float number;
	private String comment;
	private Date date;
	private Product product;

	@Column
	public Float getNumber() {
		return number;
	}

	public void setNumber(Float number) {
		this.number = number;
	}

	@Column
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "product_id")
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((comment == null) ? 0 : comment.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		RatingProduct other = (RatingProduct) obj;
		if (comment == null) {
			if (other.comment != null) {
				return false;
			}
		} else if (!comment.equals(other.comment)) {
			return false;
		}
		if (date == null) {
			if (other.date != null) {
				return false;
			}
		} else if (!date.equals(other.date)) {
			return false;
		}
		if (number == null) {
			if (other.number != null) {
				return false;
			}
		} else if (!number.equals(other.number)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "RatingProduct [number=" + number + ", comment=" + comment + ", date=" + date + ", product=" + product
				+ "]";
	}

	

	
}
