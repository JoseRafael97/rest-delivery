
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(name = "uc_menu", columnNames = { "name" }) })
@NamedQueries({ @NamedQuery(name = "menu.findSelectMenu", query = "SELECT m FROM Menu m WHERE m.ativo = TRUE"),
@NamedQuery(name = "menu.countAll", query = "SELECT COUNT(m) FROM Menu m"),@NamedQuery(name = "menu.findByProductMenu", query = "SELECT i.product.id FROM Menu m JOIN m.menuItens i WHERE i.product.id = :id GROUP BY i.product.id"),
})
public class Menu extends SuperId{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private Date date;
	private List<ItemMenu> menuItens;
	private Boolean ativo;

	public Menu() {
	}


	@NotNull
	@NotEmpty
	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date")
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	public List<ItemMenu> getMenuItens() {
		return menuItens;
	}

	public void setMenuItens(List<ItemMenu> menuItens) {
		this.menuItens = menuItens;
	}

	

	public Boolean getAtivo() {
		return ativo;
	}


	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (ativo ? 1231 : 1237);
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((menuItens == null) ? 0 : menuItens.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Menu other = (Menu) obj;
		if (ativo != other.ativo) {
			return false;
		}
		if (date == null) {
			if (other.date != null) {
				return false;
			}
		} else if (!date.equals(other.date)) {
			return false;
		}
		if (menuItens == null) {
			if (other.menuItens != null) {
				return false;
			}
		} else if (!menuItens.equals(other.menuItens)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}


	@Override
	public String toString() {
		return "Menu [name=" + name + ", date=" + date + ", menuItens=" + menuItens + ", ativo=" + ativo + "]";
	}

	


}
