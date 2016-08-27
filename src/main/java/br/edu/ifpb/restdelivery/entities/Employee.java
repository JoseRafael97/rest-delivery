package br.edu.ifpb.restdelivery.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import br.edu.ifpb.restdelivery.validators.constraints.Number;

@Entity
@PrimaryKeyJoinColumn(name = "person_id")
@Table(uniqueConstraints = { @UniqueConstraint(name = "uc_employee", columnNames = { "ctps", "tellphone", "rg" }) })
@NamedQueries({ @NamedQuery(name = "employee.findByCtps", query = "SELECT e FROM Employee e WHERE e.ctps = :ctps"),
		@NamedQuery(name = "employee.findByRg", query = "SELECT e FROM Employee e WHERE e.rg = :rg"),
		@NamedQuery(name = "employee.findByTellPhone", query = "SELECT e FROM Employee e WHERE e.tellphone = :tellphone") })
public class Employee extends Person {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String ctps;
	private String rg;
	private String tellphone;
	private Address address;

	public Employee() {

	}

	@NotNull
	@OneToOne(cascade = CascadeType.ALL)
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Column(name = "tellphone")
	public String getTellphone() {
		return tellphone;
	}

	public void setTellphone(String tellphone) {
		this.tellphone = tellphone;
	}

	@NotBlank
	@NotNull
	@Number
	@Column
	public String getCtps() {
		return ctps;
	}

	public void setCtps(String ctps) {
		this.ctps = ctps;
	}

	@NotBlank
	@NotNull
	@Column
	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((ctps == null) ? 0 : ctps.hashCode());
		result = prime * result + ((rg == null) ? 0 : rg.hashCode());
		result = prime * result + ((tellphone == null) ? 0 : tellphone.hashCode());
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
		Employee other = (Employee) obj;
		if (ctps == null) {
			if (other.ctps != null) {
				return false;
			}
		} else if (!ctps.equals(other.ctps)) {
			return false;
		}
		if (rg == null) {
			if (other.rg != null) {
				return false;
			}
		} else if (!rg.equals(other.rg)) {
			return false;
		}
		if (tellphone == null) {
			if (other.tellphone != null) {
				return false;
			}
		} else if (!tellphone.equals(other.tellphone)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Employee [id=" + this.getId() + ",ctps=" + ctps + ", rg=" + rg + ", tellphone=" + tellphone
				+ ", address=" + address + "]";
	}

}


