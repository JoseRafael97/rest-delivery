package br.edu.ifpb.restdelivery.services;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

import br.edu.ifpb.restdelivery.exceptions.RestDeliveryException;
import br.edu.ifpb.restdelivery.exceptions.RestDeliveryPersistenceException;

/**
 * 
 * @author rafaelfeitosa - <a href="https://github.com/JoseRafael97 ></a>
 *
 * @param <T> Tipo da classe.
 * @param <K> Tipo do identificador.
 */

public interface GenericService<T, K extends Serializable> {

	/**
	 * Salva objetos de acordo com o tipo passado
	 * 
	 * @param obj
	 *            - Entidade ao qual deseja salvar.
	 */
	
	public void save(@NotNull T obj) throws RestDeliveryException;

	/**
	 * Atualiza dados de um objeto de acordo com o tipo passado
	 * 
	 * @param obj
	 *            Entidade que se deseja atualizar
	 */
	
	
	public void update(@NotNull T obj) throws RestDeliveryException;

	/**
	 * Remove objetos do Banco de dados de acordo com o tipo passado
	 * 
	 * @param ob
	 *            - Entidade ao qual deseja remover.
	 */
	public void remove(@NotNull T obj) throws RestDeliveryException;

	/**
	 * Busca objetos de acordo com a chave passada e com o tipo de objeto
	 * requisitado
	 * 
	 * @param id
	 *            - Identificador do objeto buscado
	 * @return O objeto buscado.
	 */
	public T find(@NotNull K id) throws RestDeliveryException;

	/**
	 * Busca todos os objetos existente do tipo requisitado.
	 * 
	 * @return Lista de objetos do tipo da Entidade requisitada.
	 * @throws RestDeliveryPersistenceException 
	 * @throws RestDeliveryException 
	 */
	public List<T> listAll() throws RestDeliveryPersistenceException, RestDeliveryException;
}