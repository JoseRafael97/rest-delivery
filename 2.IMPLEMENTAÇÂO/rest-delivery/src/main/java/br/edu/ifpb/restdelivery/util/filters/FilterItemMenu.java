package br.edu.ifpb.restdelivery.util.filters;

import java.io.Serializable;

import br.edu.ifpb.restdelivery.enumerations.CategoryType;
import br.edu.ifpb.restdelivery.enumerations.OptionFind;

/**
 * Classe utilizada como filtro com base nos preenchimento de atributos que representam atributos da entidade ITEMMENU
 * 
 * @author rafaelfeitosa - <a href="https://github.com/JoseRafael97 ></a>
 *
 */
public class FilterItemMenu implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
	private CategoryType category;
	
	private int first;
	private int amount;
	private boolean ascendent;
	
	private OptionFind optionFind;
	private Integer rating;

	
	private Float maxPrice;
	private Float minPrice;
	
	private String propetyOrdened;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	 

	public CategoryType getCategory() {
		return category;
	}

	public void setCategory(CategoryType category) {
		this.category = category;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Float getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(Float maxPrice) {
		this.maxPrice = maxPrice;
	}

	public Float getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(Float minPrice) {
		this.minPrice = minPrice;
	}

	public OptionFind getOptionFind() {
		return optionFind;
	}

	public void setOptionFind(OptionFind optionFind) {
		this.optionFind = optionFind;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	
	
	
}
