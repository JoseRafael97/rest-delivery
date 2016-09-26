package br.edu.ifpb.restdelivery.util.filters;

import java.io.Serializable;

/**
 * Classe utilizada como filtro com base nos preenchimento de atributos que
 * representam atributos da entidade Product
 * 
 * @author rafaelfeitosa - <a href="https://github.com/JoseRafael97 ></a>
 *
 */
public class FilterProducts implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;
	private String description;
	private String category;

	private int first;
	private int amount;

	private boolean ascendent;

	private String propetyOrdened;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getFirst() {
		return first;
	}

	public void setFirst(int first) {
		this.first = first;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public boolean isAscendent() {
		return ascendent;
	}

	public void setAscendent(boolean ascendent) {
		this.ascendent = ascendent;
	}

	public String getPropetyOrdened() {
		return propetyOrdened;
	}

	public void setPropetyOrdened(String propetyOrdened) {
		this.propetyOrdened = propetyOrdened;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
