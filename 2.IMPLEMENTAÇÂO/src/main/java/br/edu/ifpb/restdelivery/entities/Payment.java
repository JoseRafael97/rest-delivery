package br.edu.ifpb.restdelivery.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import br.edu.ifpb.restdelivery.enumerations.TypesCartCreditFlag;

@Entity
@Table
public class Payment extends SuperId {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String owner;
	private String segurityCode;
	private Date paymentDate;
	private String creditCardnumber;
	private TypesCartCreditFlag flagName;


	@NotBlank
	@Column(name = "owner")
	public String getOwner() {
		return owner;
	}

	@NotBlank //@CreditCardNumber
	@Column(name = "credit_card_number")
	public String getCreditCardnumber() {
		return creditCardnumber;
	}

	public void setCreditCardnumber(String creditCardnumber) {
		this.creditCardnumber = creditCardnumber;
	}

	@NotNull
	@Enumerated(EnumType.STRING)
	public TypesCartCreditFlag getFlagName() {
		return flagName;
	}

	public void setFlagName(TypesCartCreditFlag flagName) {
		this.flagName = flagName;
	}

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "payment_date")
	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setData(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	
	@NotBlank 
	@Column(name = "segurity_code")
	public String getSegurityCode() {
		return segurityCode;
	}

	public void setSegurityCode(String segurityCode) {
		this.segurityCode = segurityCode;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((creditCardnumber == null) ? 0 : creditCardnumber.hashCode());
		result = prime * result + ((flagName == null) ? 0 : flagName.hashCode());
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
		result = prime * result + ((paymentDate == null) ? 0 : paymentDate.hashCode());
		result = prime * result + ((segurityCode == null) ? 0 : segurityCode.hashCode());
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
		Payment other = (Payment) obj;
		if (creditCardnumber == null) {
			if (other.creditCardnumber != null) {
				return false;
			}
		} else if (!creditCardnumber.equals(other.creditCardnumber)) {
			return false;
		}
		if (flagName != other.flagName) {
			return false;
		}
		if (owner == null) {
			if (other.owner != null) {
				return false;
			}
		} else if (!owner.equals(other.owner)) {
			return false;
		}
		if (paymentDate == null) {
			if (other.paymentDate != null) {
				return false;
			}
		} else if (!paymentDate.equals(other.paymentDate)) {
			return false;
		}
		if (segurityCode == null) {
			if (other.segurityCode != null) {
				return false;
			}
		} else if (!segurityCode.equals(other.segurityCode)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Payment [owner=" + owner + ", segurityCode=" + segurityCode + ", paymentDate=" + paymentDate
				+ ", creditCardnumber=" + creditCardnumber + ", flagName=" + flagName + "]";
	}
	
	

	
	
}
