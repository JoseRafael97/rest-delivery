package br.edu.ifpb.restdelivery.services.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import br.edu.ifpb.restdelivery.dao.ProductDAO;
import br.edu.ifpb.restdelivery.entities.Product;
import br.edu.ifpb.restdelivery.exceptions.RestDeliveryPersistenceException;
import br.edu.ifpb.restdelivery.util.filters.FilterProducts;
import br.edu.ifpb.restdelivery.util.jpa.Transactional;
import br.edu.ifpb.restdelivery.validators.ValidatorProduct;

/**
 * Classe que fornece serviços da entidade Product
 * 
 * @author rafaelfeitosa - <a href="https://github.com/JoseRafael97 ></a>
 *
 */
public class ProductService extends ImplGenericService<Product, Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private MenuService service;

	/**
	 * * * Construtor injetado com CDI para utilizar o DAO generico, nele
	 * passamos o tipo do dao genérico de Product.
	 * 
	 * @param productDAO
	 */
	@Inject
	public ProductService(ProductDAO productDAO) {
		this.dao = productDAO;
	}

	/**
	 * Método que remove um produto, validando se ele não está sendo utilizado
	 * por outro objeto.
	 * 
	 * @param p
	 * @throws RestDeliveryPersistenceException
	 */
	@Transactional
	public void removeProduct(Product p) throws RestDeliveryPersistenceException {
		ValidatorProduct.validateRemoveProduct(p, service);
		this.dao.remove(p);
	}

	/**
	 * Método que conta a quantidade de itens filtratos com filtro passado.
	 * 
	 * @param filterProducts
	 * @return
	 */
	public int countProductFilters(FilterProducts filterProducts) {
		return ((ProductDAO) this.dao).countProductFilters(filterProducts);
	}

	/**
	 * Método que filtra produtos com o filtro passado.
	 * 
	 * @param filterProducts
	 * @return
	 */
	public List<Product> filterProduct(FilterProducts filterProducts) {
		return ((ProductDAO) this.dao).filterProduct(filterProducts);
	}

	/**
	 * Método que conta o número de produtos cadastrados.
	 * 
	 * @return
	 */
	public Long countAll() {
		return ((ProductDAO) this.dao).countAll();
	}

	/**
	 * Método que tira a média das avaliçãoes realizadas ao produto com nome
	 * passado.
	 * 
	 * @param nameProduct
	 * @return
	 */
	public Double averageProductRating(String nameProduct) {
		return ((ProductDAO) this.dao).averageProductRating(nameProduct);
	}

	/**
	 * Método que busca o produto mais vendido
	 * 
	 * @return
	 * @throws RestDeliveryPersistenceException
	 */
	public Map<String, BigDecimal> ProductsSoBuy() throws RestDeliveryPersistenceException {
		return ((ProductDAO) this.dao).findBestRatingCategory();
	}

	/**
	 * Busca todas categorias e quantidade que aparece em todos os pedidos, ordenando pela propria categoria
	 * @return
	 * @throws RestDeliveryPersistenceException
	 */
	public Map<String, BigDecimal> findBestsellingCategory() throws RestDeliveryPersistenceException {
		return ((ProductDAO) this.dao).findBestsellingCategory();

	}
}
