package br.edu.ifpb.restdelivery.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.NotEmpty;

import br.edu.ifpb.restdelivery.enumerations.CategoryType;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(name = "uc_product", columnNames = { "name" }) })
@NamedQueries({ @NamedQuery(name = "product.filterByName", query = "SELECT p FROM Product p WHERE p.name like :name"),
		@NamedQuery(name = "product.countAll", query = "SELECT COUNT(p) FROM Product p") })
public class Product extends SuperId {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String description;
	private CategoryType category;
	private String pathFile;
	private Float weight;
	private String nutricionInformations;
	private List<RatingProduct> ratingProducts;

	public Product() {
	}

	@NotEmpty
	@NotNull
	@Column(name = "name", unique = true)
	public String getName() {
		return name;
	}

	@NotNull
	@Enumerated(EnumType.STRING)
	public CategoryType getCategory() {
		return category;
	}

	public void setCategory(CategoryType category) {
		this.category = category;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "path")
	public String getPathFile() {
		return pathFile;
	}

	public void setPathFile(String pathFile) {
		this.pathFile = pathFile;
	}

	@NotNull
	@Column
	public Float getWeight() {
		return weight;
	}

	public void setWeight(Float weight) {
		this.weight = weight;
	}

	@NotNull
	@NotEmpty
	@Column(name = "nutricion_information")
	public String getNutricionInformations() {
		return nutricionInformations;
	}

	public void setNutricionInformations(String nutricionInformations) {
		this.nutricionInformations = nutricionInformations;
	}

	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	public List<RatingProduct> getRatingProducts() {
		
		if(ratingProducts == null){
			ratingProducts = new ArrayList<>();
		}
		
		return ratingProducts;
	}

	public void setRatingProducts(List<RatingProduct> ratingProducts) {
		this.ratingProducts = ratingProducts;
	}

}
