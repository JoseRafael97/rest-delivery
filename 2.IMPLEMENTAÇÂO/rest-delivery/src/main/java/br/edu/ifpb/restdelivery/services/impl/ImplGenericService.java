package br.edu.ifpb.restdelivery.services.impl;

import java.io.Serializable;
import java.util.List;

import br.edu.ifpb.restdelivery.dao.GenericDAO;
import br.edu.ifpb.restdelivery.exceptions.RestDeliveryException;
import br.edu.ifpb.restdelivery.exceptions.RestDeliveryPersistenceException;
import br.edu.ifpb.restdelivery.services.GenericService;
import br.edu.ifpb.restdelivery.util.jpa.Transactional;

/**
 * Implementação da interface do GenericService 
 * 
 * @author rafaelfeitosa - <a href="https://github.com/JoseRafael97 ></a>
 *
 * @param <T> - Tipo da classe
 * @param <K> - tipo do identificador
 */
public class ImplGenericService<T, K extends Serializable> implements GenericService<T, K>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected GenericDAO<T, K> dao;

	/**
	 * Método que salva o objeto no banco de dados.
	 */
	@Transactional
	public void save(T obj) throws RestDeliveryException {
		dao.add(obj);
	}


	/**
	 * Método atualiza o objeto com as informações do objeto passado no parâmetro
	 */
	@Override
	@Transactional
	public void update(T obj) throws RestDeliveryException {
		dao.update(obj);
	}

	/**
	 * Método que remove o objeto do banco de dados de acordo com objeto passado no parâmetro
	 */
	@Override
	@Transactional
	public void remove(T obj) throws RestDeliveryException {
		dao.remove(obj);
	}

	/**
	 * Método que busca um objeto de acordo com o identificador passado no parâmetro
	 */
	@Override
	public T find(K id) throws RestDeliveryException {
		return dao.find(id);
	}

	/**
	 * Método que busca todos os objetos do tipo da classe
	 * @throws RestDeliveryException 
	 */
	@Override
	public List<T> listAll() throws RestDeliveryPersistenceException, RestDeliveryException {
		return dao.findAll();
	}

}
