package br.edu.ifpb.restdelivery.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "tab_order")
@NamedQueries({ @NamedQuery(name = "order.countAll", query = "SELECT COUNT(o) FROM Order o"),
		@NamedQuery(name = "order.countOrderRating", query = "SELECT COUNT(o) FROM Order o where o.ratingOrder.number IS NOT NULL") })
public class Order extends SuperId {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date data;
	private Float totalprice;
	private Payment payment;
	private List<ItemProduct> itemProducts;
	private Delivery delivery;
	private RatingOrder ratingOrder;
	private Customer customer;

	public Order() {

	}

	@NotNull
	@ManyToOne
	@JoinColumn(name = "order_id")
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade = { CascadeType.ALL })
	@JoinColumn(name = "order_fk")
	public List<ItemProduct> getItemProducts() {
		return itemProducts;
	}

	public void setItemProducts(List<ItemProduct> itemProducts) {
		this.itemProducts = itemProducts;
	}

	@OneToOne(cascade = CascadeType.ALL)
	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	@OneToOne(cascade = CascadeType.ALL)
	public Delivery getDelivery() {
		return delivery;
	}

	public void setDelivery(Delivery delivery) {
		this.delivery = delivery;
	}

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date")
	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	@DecimalMin(value = "0.1", message = "preço inválido")
	@Column(name = "totalprice")
	public Float getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(Float totalprice) {
		this.totalprice = totalprice;
	}

	@Embedded
	public RatingOrder getRatingOrder() {
		return ratingOrder;
	}

	public void setRatingOrder(RatingOrder ratingOrder) {
		this.ratingOrder = ratingOrder;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((delivery == null) ? 0 : delivery.hashCode());
		result = prime * result + ((itemProducts == null) ? 0 : itemProducts.hashCode());
		result = prime * result + ((payment == null) ? 0 : payment.hashCode());
		result = prime * result + ((ratingOrder == null) ? 0 : ratingOrder.hashCode());
		result = prime * result + ((totalprice == null) ? 0 : totalprice.hashCode());
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
		Order other = (Order) obj;
		if (customer == null) {
			if (other.customer != null) {
				return false;
			}
		} else if (!customer.equals(other.customer)) {
			return false;
		}
		if (data == null) {
			if (other.data != null) {
				return false;
			}
		} else if (!data.equals(other.data)) {
			return false;
		}
		if (delivery == null) {
			if (other.delivery != null) {
				return false;
			}
		} else if (!delivery.equals(other.delivery)) {
			return false;
		}
		if (itemProducts == null) {
			if (other.itemProducts != null) {
				return false;
			}
		} else if (!itemProducts.equals(other.itemProducts)) {
			return false;
		}
		if (payment == null) {
			if (other.payment != null) {
				return false;
			}
		} else if (!payment.equals(other.payment)) {
			return false;
		}
		if (ratingOrder == null) {
			if (other.ratingOrder != null) {
				return false;
			}
		} else if (!ratingOrder.equals(other.ratingOrder)) {
			return false;
		}
		if (totalprice == null) {
			if (other.totalprice != null) {
				return false;
			}
		} else if (!totalprice.equals(other.totalprice)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Order [data=" + data + ", totalprice=" + totalprice + ", payment=" + payment + ", itemProducts="
				+ itemProducts + ", delivery=" + delivery + ", ratingOrder=" + ratingOrder + "]";
	}

}
