package br.edu.ifpb.restdelivery.dao;

import java.io.Serializable;
import java.util.List;

import br.edu.ifpb.restdelivery.exceptions.RestDeliveryPersistenceException;
/**
 * 
 * @author rafaelfeitosa - <a href="https://github.com/JoseRafael97 ></a>
 *
@param <T> Tipo da Classe
 *
 * @param <K> Tipo do ID
 */
public interface GenericDAO<T, K extends Serializable> {
	
	/**
	 * Cria um novo objeto
	 * 
	 * @param object
	 * @throws RestDeliveryPersistenceException
	 */
	public void add(T object) throws RestDeliveryPersistenceException;

	/**
	 * Remove um objeto do banco
	 * 
	 * @param key
	 *            identificador do objeto
	 */
	public void remove(T object) throws RestDeliveryPersistenceException;

	/**
	 * Atualiza informações do objeto
	 * 
	 * @param key
	 *            idetificador do objeto
	 * @param object
	 *            objeto alterado
	 */

	public void update(T object) throws RestDeliveryPersistenceException;

	/**
	 * Busca no banco o objeto com o identificador passado.
	 * 
	 * @param key
	 *            idetificador do objeto.
	 * @return O objeto correpondente a chave idetificadora.
	 */

	public T find(K key) throws RestDeliveryPersistenceException;

	/**
	 * Busca todos os objetos do tipo existente no Banco.
	 * 
	 * @return um lista com todos os objetos.
	 */
	public List<T> findAll() throws RestDeliveryPersistenceException;

}
