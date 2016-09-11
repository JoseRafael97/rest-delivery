package br.edu.ifpb.restdelivery.dao.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.time.DateUtils;

import br.edu.ifpb.restdelivery.dao.OrderDAO;
import br.edu.ifpb.restdelivery.entities.Order;
import br.edu.ifpb.restdelivery.exceptions.RestDeliveryPersistenceException;

/**
 * Implementação do DAO de pedido e subclasse da implementação de GenericDAO.
 * 
 * @author rafaelfeitosa - <a href="https://github.com/JoseRafael97 ></a>
 *
 */
public class ImplOrderDAO extends ImplGenericDAO<Order, Long> implements OrderDAO, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Método que filtra os valores arrecadados em pedidos dividos por data.
	 */
	@SuppressWarnings("unchecked")
	public Map<Date, BigDecimal> allValuesToDate(Integer numberDays) throws RestDeliveryPersistenceException {

		numberDays -= 1;
		Calendar initialDate = Calendar.getInstance();
		initialDate = DateUtils.truncate(initialDate, Calendar.DAY_OF_MONTH);
		initialDate.add(Calendar.DAY_OF_MONTH, numberDays * -1);

		Map<Date, BigDecimal> result = createMap(numberDays, initialDate);

		EntityManager em = getEntityManager();
		List<Object[]> resultado = null;
		try {
			Query query = em.createQuery(
					"select o.data, sum(o.totalprice) from Order o where o.data >= :initialDate group by o.data");
			query.setParameter("initialDate", initialDate.getTime());
			resultado = query.getResultList();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new RestDeliveryPersistenceException(
					"Ocorreu algum erro ao tentar recuperar as pessoas que tem cachorro com peso maior que o valor passado como parâmetro.",
					pe);
		}

		if (resultado != null) {
			for (int i = 0; i < resultado.size(); i++) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime((Date) (resultado.get(i)[0]));

				result.put(DateUtils.truncate(calendar, Calendar.DAY_OF_MONTH).getTime(),
						new BigDecimal(((Double) (resultado.get(i))[1])));
			}
		}

		return result;
	}

	
	/**
	 * Cria um mapa para evitar um mapa vazio, informando o numero de dias para o mapa e data inicial.
	 */
	public Map<Date, BigDecimal> createMap(Integer numberDays, Calendar initialDate) {

		Map<Date, BigDecimal> initialMap = new TreeMap<>();
		initialDate = (Calendar) initialDate.clone();

		for (int i = 0; i <= numberDays; i++) {
			initialMap.put(initialDate.getTime(), BigDecimal.ZERO);
			initialDate.add(Calendar.DAY_OF_MONTH, 1);
		}
		return initialMap;
	}

	/**
	 * Método que contar o  numero total de pedidos já cadastrados.
	 */
	public Long countAll() {
		try {
			TypedQuery<Long> query = em.createNamedQuery("order.countAll", Long.class);
			return query.getSingleResult();

		} catch (NoResultException e) {
			return null;
		}

	}
	

	@Override
	public Long countRatingAll() {
		try {
			TypedQuery<Long> query = em.createNamedQuery("order.countOrderRating", Long.class);
			return query.getSingleResult();

		} catch (NoResultException e) {
			return null;
		}
		
	}


	@Override
	public List<Order> findOrderToRating() {
		try {
			TypedQuery<Order> query = em.createNamedQuery("order.findOrderToRating", Order.class);
			query.setMaxResults(3);
			return query.getResultList();

		} catch (NoResultException e) {
			return null;
		}
		
	}

}
