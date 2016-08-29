package br.edu.ifpb.restdelivery.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaQuery;

import br.edu.ifpb.restdelivery.entities.Product;
import br.edu.ifpb.restdelivery.exceptions.RestDeliveryPersistenceException;
import br.edu.ifpb.restdelivery.util.filters.FilterProducts;

/**
 *Interface DAO para Product.
 * 
 * @author rafaelfeitosa - <a href="https://github.com/JoseRafael97 ></a>
 *

 */
public interface ProductDAO extends GenericDAO<Product, Long>{
	
/**
	 * Filtra produtos por numero de registro requisitado e nome.
	 * @param filterProducts - filtros para produto, ex: filtra 40 primeiros em ordem crescente por id.
	 * @return Lista de produtos
	 */
	public List<Product> filterProduct(FilterProducts filterProducts);
	
	
	/**
	 * Busca a quantidade total de produtos cadastrados
	 * @param filterProducts
	 * @return numero de produtos cadastrado.
	 */
	public int countProductFilters(FilterProducts filterProducts);
	
	
	/**
	 * Cria CriteriaQuery com alguns filtros já colococados.
	 * @param filterProducts
	 * @return
	 */
	public CriteriaQuery<Product> createFilterToProduct(FilterProducts filterProducts);
	
	/**
	 * Método que conta todos os produtos cadastrados.
	 * @return o número de produtos cadastrado.
	 */
	public Long countAll ();
	
	/**
	 * Método que busca as categorias ordenadas pelas que foram mais vendidas.
	 * @return
	 * @throws RestDeliveryPersistenceException
	 */
	public Map<String, BigDecimal> findBestsellingCategory() throws RestDeliveryPersistenceException;
	
	/**
	 * Método que tira a média das avalições feitas nos produtos.
	 * @param nameProduct
	 * @return
	 */
	public Double averageProductRating(String nameProduct);
	
	/**
	 * Método que busca as categorias em ordem de melhores avaliadas pelos cliente.
	 * @return
	 * @throws RestDeliveryPersistenceException
	 */
	public Map<String, BigDecimal> findBestRatingCategory() throws RestDeliveryPersistenceException;
	
	/**
	 * Método que cria um MAPA vazio para evitar null pointer exeception, e coloca os valores 0 como inicial.
	 * @return
	 */
	public Map<String, BigDecimal> createMap();
	

}
