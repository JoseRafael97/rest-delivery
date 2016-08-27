package br.edu.ifpb.restdelivery.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

@Entity
@Table
@NamedQueries({
@NamedQuery(name = "itemMenu.findByCategoryItem", query = "SELECT im FROM ItemMenu im INNER JOIN im.product p WHERE p.category LIKE :category"),
@NamedQuery(name = "findByItemMenuWithMenuActive" , query = "SELECT im FROM ItemMenu im INNER JOIN im.menu m WHERE m.ativo = TRUE")})
public class ItemMenu extends SuperId {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Float price;
	private Product product;
	
	private Menu menu;

	public ItemMenu() {
	}

	

	@NotNull
	@DecimalMin(value = "0.1", message ="Preço inválido")
	@Column(name = "price", scale = 2, nullable = false)
	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_product", nullable = false )
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}


	@NotNull
	@ManyToOne
	@JoinColumn(name = "menu_id")
	public Menu getMenu() {
		return menu;
	}



	public void setMenu(Menu menu) {
		this.menu = menu;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((menu == null) ? 0 : menu.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
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
		ItemMenu other = (ItemMenu) obj;
		if (menu == null) {
			if (other.menu != null) {
				return false;
			}
		} else if (!menu.equals(other.menu)) {
			return false;
		}
		if (price == null) {
			if (other.price != null) {
				return false;
			}
		} else if (!price.equals(other.price)) {
			return false;
		}
		if (product == null) {
			if (other.product != null) {
				return false;
			}
		} else if (!product.equals(other.product)) {
			return false;
		}
		return true;
	}



	@Override
	public String toString() {
		return "ItemMenu [price=" + price + ", product=" + product + ", menu=" + menu + "]";
	}



	
}
