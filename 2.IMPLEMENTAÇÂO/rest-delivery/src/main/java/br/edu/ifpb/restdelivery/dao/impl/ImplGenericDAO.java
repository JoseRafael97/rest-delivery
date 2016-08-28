package br.edu.ifpb.restdelivery.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.hibernate.Session;

import br.edu.ifpb.restdelivery.dao.GenericDAO;
import br.edu.ifpb.restdelivery.exceptions.RestDeliveryPersistenceException;

/**
 * @author rafaelfeitosa
 *
 * @param <T>
 *            Tipo da classe
 * @param <K>
 *            Tipo do identificador
 */
public class ImplGenericDAO<T, K extends Serializable> implements GenericDAO<T, K> {

	@Inject
	protected EntityManager em;

	private Class<T> persistentClass;

	/**
	 * Método construtor que verifcar o tipo da classe passado para direcionar a persitência para esse tipo.
	 */
	@SuppressWarnings("unchecked")
	public ImplGenericDAO() {
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];

	}

	/**
	 * Método que adciona um objeto no banco de dados de acordo com tipo de objeto passado.
	 */
	public void add(T object) throws RestDeliveryPersistenceException {

		try {
			em.persist(object);

		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new RestDeliveryPersistenceException(
					"Não foi possível adicionar");
		}
	}

	/**
	 * Método que remove do banco o objeto passado como parâmento, se o mesmo existir realmente no banco.
	 */
	public void remove(T object) throws RestDeliveryPersistenceException {
		try {
			em.remove(em.merge(object));

		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new RestDeliveryPersistenceException("Não foi possível remover " + object.getClass().getName());
		}
	}

	/**
	 * Atualiza um objeto qualquer no banco com informação do objeto passado como parâmentro.
	 */
	public void update(T object) throws RestDeliveryPersistenceException {
		try {
			em.merge(object);

		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new RestDeliveryPersistenceException("Não foi possível atualizar " + object.getClass().getName());
		}
	}

	/**
	 * Busca todos os objetos do tipo específicado.
	 */
	@SuppressWarnings("unchecked")
	public List<T> findAll() throws RestDeliveryPersistenceException {
		try {
			Query query = em.createQuery("select t from " + persistentClass.getName() + " t");
			return query.getResultList();

		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new RestDeliveryPersistenceException("Não foi possível completar a consulta");
		}
	}

	/**
	 * Busca um objeto do tipo específicado, que possui o mesmo identificador passado como parâmetro.
	 */
	@Override
	public T find(K key) throws RestDeliveryPersistenceException {
		try {

			T instance = em.find(persistentClass, key);
			return instance;

		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new RestDeliveryPersistenceException("Não existe nenhum objeto com essa chave");
		}
	}

	/**
	 * Recupera a instância do entity manager injetada.
	 * @return entitymanager
	 */
	protected EntityManager getEntityManager() {
		return em;
	}
	

	/**
	 * Recupera a sessão atual.
	 * @return
	 */
	protected Session getSession() {
		return em.unwrap(Session.class);
	}

}
