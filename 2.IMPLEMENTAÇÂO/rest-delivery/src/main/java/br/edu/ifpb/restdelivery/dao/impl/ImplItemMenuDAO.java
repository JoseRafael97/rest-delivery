package br.edu.ifpb.restdelivery.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import br.edu.ifpb.restdelivery.dao.ItemMenuDAO;
import br.edu.ifpb.restdelivery.entities.ItemMenu;
import br.edu.ifpb.restdelivery.entities.Menu;
import br.edu.ifpb.restdelivery.entities.Product;
import br.edu.ifpb.restdelivery.entities.RatingProduct;
import br.edu.ifpb.restdelivery.enumerations.CategoryType;
import br.edu.ifpb.restdelivery.exceptions.RestDeliveryPersistenceException;
import br.edu.ifpb.restdelivery.util.filters.FilterItemMenu;

/**
 * Implementação DAO de Item do cardápio e subclasse da implementação de
 * GenericDAO.
 * 
 * @author rafaelfeitosa - <a href="https://github.com/JoseRafael97 ></a>
 *
 */
public class ImplItemMenuDAO extends ImplGenericDAO<ItemMenu, Long> implements ItemMenuDAO, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Método que busca no banco de dados os itens de acordo com o
	 * FILTERITEMMENU passado como parâmetro.
	 */
	public List<ItemMenu> getItemMenuWithMenuThan(FilterItemMenu filter) throws RestDeliveryPersistenceException {
		List<ItemMenu> resultado = null;
		try {

			CriteriaQuery<ItemMenu> query = createCriteriaItemMenu(filter);

			TypedQuery<ItemMenu> typedQuery = em.createQuery(query).setFirstResult(filter.getFirst())
					.setMaxResults(filter.getAmount());
			resultado = typedQuery.getResultList();

		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new RestDeliveryPersistenceException("Ocorreu algum erro ao tentar recuperar os Itens de menu.", pe);

		}
		return resultado;
	}

	/**
	 * Método que conta o número de itens filtrados.
	 */
	public int countItemMenuFilters(FilterItemMenu filter) {
		CriteriaQuery<ItemMenu> query = createCriteriaItemMenu(filter);

		TypedQuery<ItemMenu> typedQuery = em.createQuery(query);

		return typedQuery.getResultList().size();
	}

	/**
	 * Método cria um criteriaQuery para ser utulizado em filtros.
	 * 
	 * @param filter
	 * @return
	 */
	public CriteriaQuery<ItemMenu> createCriteriaItemMenu(FilterItemMenu filter) {

		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<ItemMenu> query = cb.createQuery(ItemMenu.class);
		Root<ItemMenu> fromItemMenu = query.from(ItemMenu.class);

		query.select(fromItemMenu);

		Join<ItemMenu, Menu> joinMenu = fromItemMenu.join("menu");
		Predicate condition = cb.equal(joinMenu.<Boolean> get("ativo"), new Boolean(true));

		Join<ItemMenu, Product> joinProduct = fromItemMenu.join("product");

		if (filter.getMaxPrice() != null && filter.getMinPrice() != null) {
			condition = cb.and(condition,
					cb.between(fromItemMenu.<Float> get("price"), filter.getMinPrice(), filter.getMaxPrice()));
		}

		if (filter.getCategory() != null) {
			condition = cb.and(condition, cb.equal(joinProduct.<CategoryType> get("category"), filter.getCategory()));
		}

		if (filter.getName() != null && !filter.getName().trim().isEmpty()) {
			condition = cb.and(condition,
					cb.like(cb.lower(joinProduct.<String> get("name")), "%" + filter.getName().toLowerCase() + "%"));
		}

		if (filter.getRating() != null) {
			Subquery<Double> sq = query.subquery(Double.class);
			Root<RatingProduct> joinRat = sq.from(RatingProduct.class);
			Join<RatingProduct, Product> joinProduct2 = joinRat.join("product");
			sq.select(cb.avg(joinRat.get("number")));
			sq.where(cb.equal(joinProduct.<Long> get("id"), joinProduct2.<Long> get("id")));
			condition = cb.and(condition, cb.equal(sq, new Double(filter.getRating())));
		}
		
		if (filter.getOptionFind() != null){
			
		}

		query.where(condition);

		query.groupBy(fromItemMenu.<Long> get("id"));

		return query;

	}

}
