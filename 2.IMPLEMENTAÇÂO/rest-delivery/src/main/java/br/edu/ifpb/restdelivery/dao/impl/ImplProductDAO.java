package br.edu.ifpb.restdelivery.dao.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


import br.edu.ifpb.restdelivery.dao.ProductDAO;
import br.edu.ifpb.restdelivery.entities.Product;
import br.edu.ifpb.restdelivery.enumerations.CategoryType;
import br.edu.ifpb.restdelivery.exceptions.RestDeliveryPersistenceException;
import br.edu.ifpb.restdelivery.util.filters.FilterProducts;

public class ImplProductDAO extends ImplGenericDAO<Product, Long> implements ProductDAO, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*/**
	 * Método filtra os produtos por meio de um FILTERPRODUCT criado, por ex. por nome.
	 */
	
	public List<Product> filterProduct(FilterProducts filterProducts) {
		CriteriaQuery<Product> criteriaQuery = createFilterToProduct(filterProducts);

		TypedQuery<Product> typedQuery = em.createQuery(criteriaQuery).setFirstResult(filterProducts.getFirst())
				.setMaxResults(filterProducts.getAmount());

		return typedQuery.getResultList();
	}

	public int countProductFilters(FilterProducts filterProducts) {
		CriteriaQuery<Product> criteriaQuery = createFilterToProduct(filterProducts);

		TypedQuery<Product> typedQuery = em.createQuery(criteriaQuery);

		return typedQuery.getResultList().size();
	}

	public CriteriaQuery<Product> createFilterToProduct(FilterProducts filterProducts) {

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Product> criteriaQuery = builder.createQuery(Product.class);
		Root<Product> from = criteriaQuery.from(Product.class);

		criteriaQuery.select(from);

		Predicate predicate = builder.and();

		if (filterProducts.getName() != null && !(filterProducts.getName().isEmpty())) {
			predicate = builder.and(predicate, builder.like(from.get("name"), "%" + filterProducts.getName() + "%"));
		}

		criteriaQuery.where(predicate);

		return criteriaQuery;

	}

	/**
	 * Método conta a quantidade de produtos existentes.
	 */
	@Override
	public Long countAll() {
		try {
			TypedQuery<Long> query = em.createNamedQuery("product.countAll", Long.class);
			return query.getSingleResult();

		} catch (NoResultException e) {
			return null;
		}

	}

	/**
	 * Método tira média de avaliações ao produto com nome passado com parâmeto.
	 */
	@Override
	public Double averageProductRating(String nameProduct) {
		try {
			TypedQuery<Double> query = em.createNamedQuery("ratingProduct.averageProductRating", Double.class);
			query.setParameter("nameProduct", nameProduct);
			return query.getSingleResult();

		} catch (NoResultException e) {
			return null;
		}
	}

	/**
	 * Método retorna as categorias e as médias de avaliação total de todos os produtos de tal categoria.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, BigDecimal> findBestRatingCategory() throws RestDeliveryPersistenceException {

		Map<String, BigDecimal> result = createMap();

		EntityManager em = getEntityManager();
		List<Object[]> resultado = null;
		try {
			Query query = em.createQuery("SELECT p.category, AVG(r.number) FROM RatingProduct r JOIN r.product p group by p.category");

			resultado = query.getResultList();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new RestDeliveryPersistenceException(
					"Ocorreu algum erro ao tentar recuperar as pessoas que tem cachorro com peso maior que o valor passado como parâmetro.",
					pe);
		}

		if (resultado != null) {
			for (int i = 0; i < resultado.size(); i++) {

				CategoryType cate = ((CategoryType)(resultado.get(i))[0]);
				
				result.put(cate.getNome(),
						new BigDecimal(((Double) (resultado.get(i))[1])));
			}
		}

		return result;

	}
	
	/**
	 * Busca todas categorias e quantidade que aparece em todos os pedidos, ordenando pela propria categoria
	 */
	@SuppressWarnings("unchecked")
	public Map<String, BigDecimal> findBestsellingCategory() throws RestDeliveryPersistenceException {

		Map<String, BigDecimal> result = createMap();

		EntityManager em = getEntityManager();
		List<Object[]> resultado = null;
		try {
			Query query = em.createNamedQuery("itemProduct.findBestsellingCategory");
			resultado = query.getResultList();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new RestDeliveryPersistenceException(
					"Ocorreu algum erro ao tentar recuperar as pessoas que tem cachorro com peso maior que o valor passado como parâmetro.",
					pe);
		}

		if (resultado != null) {
			for (int i = 0; i < resultado.size(); i++) {

				CategoryType cate = ((CategoryType)(resultado.get(i))[0]);
				
				result.put(cate.getNome(),
						new BigDecimal(((Long) (resultado.get(i))[1])));
			}
		}

		return result;

	}

	/**
	 * Cria um mapa vazio com valores zerados para não causar nullpoiterexception e apresentar dados zerados se eles não existirem.
	 */
	@Override
	public Map<String, BigDecimal> createMap() {
		Map<String, BigDecimal> initialMap = new TreeMap<>();

		for (int i = 0; i < CategoryType.values().length; i++) {
			initialMap.put(CategoryType.values()[i].getNome(), BigDecimal.ZERO);
			
		}
		
		return initialMap;
	}
	
	

	
	

}