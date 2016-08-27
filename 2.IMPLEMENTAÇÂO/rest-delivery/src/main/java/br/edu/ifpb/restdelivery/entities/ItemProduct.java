package br.edu.ifpb.restdelivery.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;

import com.sun.istack.NotNull;


@Entity
@Table
public class ItemProduct extends SuperId{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer amount;
	private Float price;
	private ItemMenu itemMenu;

	public ItemProduct() {
	}


	@NotNull
	@ManyToOne
	public ItemMenu getItemMenu() {
		return itemMenu;
	}

	public void setItemMenu(ItemMenu itemMenu) {
		this.itemMenu = itemMenu;
	}

	@Column(name = "amount", nullable = false)
	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	@DecimalMin(value = "0.1", message ="Preço informado é inválido")
	@Column(name = "price", scale = 2, nullable = false)
	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((itemMenu == null) ? 0 : itemMenu.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
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
		ItemProduct other = (ItemProduct) obj;
		if (amount == null) {
			if (other.amount != null) {
				return false;
			}
		} else if (!amount.equals(other.amount)) {
			return false;
		}
		if (itemMenu == null) {
			if (other.itemMenu != null) {
				return false;
			}
		} else if (!itemMenu.equals(other.itemMenu)) {
			return false;
		}
		if (price == null) {
			if (other.price != null) {
				return false;
			}
		} else if (!price.equals(other.price)) {
			return false;
		}
		return true;
	}


	@Override
	public String toString() {
		return "ItemProduct [amount=" + amount + ", price=" + price + ", itemMenu=" + itemMenu + "]";
	}


	
	

}
