package br.edu.ifpb.restdelivery.dao.impl;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.time.DateUtils;

import br.edu.ifpb.restdelivery.dao.CustomerDAO;
import br.edu.ifpb.restdelivery.entities.Customer;

/**
 * Implementação do DAO de cliente e do DAO Generico
 * 
 * @author rafaelfeitosa - <a href="https://github.com/JoseRafael97 ></a>
 *
 */
public class ImplCustomerDAO extends ImplGenericDAO<Customer, Long> implements CustomerDAO, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Método que busca no banco o número de clientes existentes.
	 */
	@Override
	public Long countAll() {
		try {
			TypedQuery<Long> query = em.createNamedQuery("customer.countAll", Long.class);
			return query.getSingleResult();

		} catch (NoResultException e) {
			return null;
		}

	}

	/**
	 * Cria um mapa para evitar um mapa vazio, informando o numero de dias para
	 * o mapa e data inicial.
	 */
	private Map<Date, Long> createMap(Integer numberDays, Calendar initialDate) {

		Map<Date, Long> initialMap = new TreeMap<>();
		initialDate = (Calendar) initialDate.clone();

		for (int i = 0; i <= numberDays; i++) {
			initialMap.put(initialDate.getTime(), 0l);
			initialDate.add(Calendar.DAY_OF_MONTH, 1);
		}
		return initialMap;
	}

	@Override
	public Map<Date, Long> findCustomerToDate(Integer numberDays) {
		numberDays -= 1;
		Calendar initialDate = Calendar.getInstance();
		initialDate = DateUtils.truncate(initialDate, Calendar.DAY_OF_MONTH);
		initialDate.add(Calendar.DAY_OF_MONTH, numberDays * -1);

		Map<Date, Long> values = createMap(numberDays, initialDate);

		EntityManager em = getEntityManager();

		List<Object[]> resultado = null;

		TypedQuery<Object[]> query = em.createNamedQuery("customer.findCustomerToDate", Object[].class);
		resultado = query.getResultList();

		if (resultado != null) {
			for (int i = 0; i < resultado.size(); i++) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime((Date) (resultado.get(i)[0]));

				values.put(DateUtils.truncate(calendar, Calendar.DAY_OF_MONTH).getTime(), (Long) (resultado.get(i))[1]);
			}
		}

		return values;
	}

	public Map<Date, Long> findCustomerToDateToSexo(Integer numberDays, String sexo) {
		numberDays -= 1;
		Calendar initialDate = Calendar.getInstance();
		initialDate = DateUtils.truncate(initialDate, Calendar.DAY_OF_MONTH);
		initialDate.add(Calendar.DAY_OF_MONTH, numberDays * -1);

		Map<Date, Long> values = createMap(numberDays, initialDate);

		EntityManager em = getEntityManager();

		List<Object[]> resultado = null;

		TypedQuery<Object[]> query = em.createNamedQuery("customer.findCustomerToDateToSexo", Object[].class);
		query.setParameter("sexo", sexo.charAt(0));
		resultado = query.getResultList();

		if (resultado != null) {
			for (int i = 0; i < resultado.size(); i++) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime((Date) (resultado.get(i)[0]));

				values.put(DateUtils.truncate(calendar, Calendar.DAY_OF_MONTH).getTime(), (Long) (resultado.get(i))[1]);
			}
		}

		return values;
	}
}
