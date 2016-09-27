package br.edu.ifpb.restdelivery.entities;


import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Classe que representa um cliente do restaurante.
 * 
 * @author rafaelfeitosa - <a href="https://github.com/JoseRafael97 ></a>
 *
 */

@Entity
@PrimaryKeyJoinColumn(name = "person_id")
@NamedQueries({ @NamedQuery(name = "customer.countAll", query = "SELECT COUNT(c) FROM Customer c"),
	@NamedQuery(name = "customer.findCustomerToDate", query ="SELECT c.date, COUNT(c) FROM Customer c GROUP BY c.date ORDER BY c.date DESC"),
	@NamedQuery(name = "customer.findCustomerToDateToSexo", query ="SELECT c.date, COUNT(c) FROM Customer c WHERE c.sexo = :sexo GROUP BY c.date ORDER BY c.date DESC")})
public class Customer extends Person {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;
	private Address address;
	private List<Order> orders;
	private Date date;

	public Customer() {
	}

	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_register")
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((orders == null) ? 0 : orders.hashCode());
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
		Customer other = (Customer) obj;
		if (address == null) {
			if (other.address != null) {
				return false;
			}
		} else if (!address.equals(other.address)) {
			return false;
		}
		if (date == null) {
			if (other.date != null) {
				return false;
			}
		} else if (!date.equals(other.date)) {
			return false;
		}
		if (orders == null) {
			if (other.orders != null) {
				return false;
			}
		} else if (!orders.equals(other.orders)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Customer [address=" + address + ", orders=" + orders + ", date=" + date + "]";
	}

	
	
}
