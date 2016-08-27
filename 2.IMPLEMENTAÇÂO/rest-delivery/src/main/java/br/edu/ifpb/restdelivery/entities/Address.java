package br.edu.ifpb.restdelivery.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import br.edu.ifpb.restdelivery.validators.constraints.CEP;
import br.edu.ifpb.restdelivery.validators.constraints.Number;

/**
 * Entidade que representa um Endereço de um cliente ou funcionário.
 * 
 * @author rafaelfeitosa
 *
 */

@Entity
@Table
@NamedQueries({@NamedQuery(name = "address.findByStreet", query = "SELECT a FROM Address a where a.street = :street")})
public class Address extends SuperId {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String street;
	private String complement;
	private String neighborhood;
	private String city;
	private String state;
	private String zipCode;
	private String number;

	public Address() {

	}


	@NotEmpty
	@NotNull
	@Column(name = "street", nullable = true)
	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	@NotBlank
	@NotNull
	@Column(name = "neighborhood", nullable = true)
	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	@NotBlank
	@NotNull
	@Column(name = "city", nullable = true)
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@NotEmpty
	@NotNull
	@Column(nullable = true)
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column
	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	@NotNull
	@NotEmpty
	@CEP
	@Column(name = "zip_code", nullable = false)
	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	@NotEmpty
	@NotNull
	@Number
	@Column(name = "house_number", nullable = false)
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((complement == null) ? 0 : complement.hashCode());
		result = prime * result + ((neighborhood == null) ? 0 : neighborhood.hashCode());
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((street == null) ? 0 : street.hashCode());
		result = prime * result + ((zipCode == null) ? 0 : zipCode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Address other = (Address) obj;
		if (city == null) {
			if (other.city != null) {
				return false;
			}
		} else if (!city.equals(other.city)) {
			return false;
		}
		if (complement == null) {
			if (other.complement != null) {
				return false;
			}
		} else if (!complement.equals(other.complement)) {
			return false;
		}
		if (neighborhood == null) {
			if (other.neighborhood != null) {
				return false;
			}
		} else if (!neighborhood.equals(other.neighborhood)) {
			return false;
		}
		if (number == null) {
			if (other.number != null) {
				return false;
			}
		} else if (!number.equals(other.number)) {
			return false;
		}
		if (state == null) {
			if (other.state != null) {
				return false;
			}
		} else if (!state.equals(other.state)) {
			return false;
		}
		if (street == null) {
			if (other.street != null) {
				return false;
			}
		} else if (!street.equals(other.street)) {
			return false;
		}
		if (zipCode == null) {
			if (other.zipCode != null) {
				return false;
			}
		} else if (!zipCode.equals(other.zipCode)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Address [street=" + street + ", complement=" + complement + ", neighborhood=" + neighborhood + ", city="
				+ city + ", state=" + state + ", zipCode=" + zipCode + ", number=" + number + "]";
	}
	
	

}
