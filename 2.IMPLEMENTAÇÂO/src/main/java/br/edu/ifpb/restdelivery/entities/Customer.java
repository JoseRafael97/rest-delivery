package br.edu.ifpb.restdelivery.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "person_id")
@NamedQueries({ @NamedQuery(name = "customer.countAll", query = "SELECT COUNT(c) FROM Customer c") })
public class Customer extends Person {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;
	private Address address;
	private List<Order> orders;

	public Customer() {
	}

	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((address == null) ? 0 : address.hashCode());
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
		return "Customer [address=" + address + ", orders=" + orders + "]";
	}

	
	
}
