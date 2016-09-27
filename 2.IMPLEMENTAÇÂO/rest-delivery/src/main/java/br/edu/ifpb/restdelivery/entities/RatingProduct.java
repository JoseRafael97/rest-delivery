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

/**
 * Classe que representa uma avaliação de um produto (item) do restaurante. 
 * 
 * @author rafaelfeitosa - <a href="https://github.com/JoseRafael97 ></a>
 *
 */

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
	private String userNameCustomer;

	@Column @NotNull
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
	
	
	//@NotNull
	@Column(name = "user_name_customer")
	public String getUserNameCustomer() {
		return userNameCustomer;
	}

	public void setUserNameCustomer(String userNameCustomer) {
		this.userNameCustomer = userNameCustomer;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((comment == null) ? 0 : comment.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime * result + ((userNameCustomer == null) ? 0 : userNameCustomer.hashCode());
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
		if (product == null) {
			if (other.product != null) {
				return false;
			}
		} else if (!product.equals(other.product)) {
			return false;
		}
		if (userNameCustomer == null) {
			if (other.userNameCustomer != null) {
				return false;
			}
		} else if (!userNameCustomer.equals(other.userNameCustomer)) {
			return false;
		}
		return true;
	}

	
	@Override
	public String toString() {
		return "RatingProduct [number=" + number + ", comment=" + comment + ", date=" + date + ", product=" + product
				+ ", userNameCustomer=" + userNameCustomer + "]";
	}

	
	
}
