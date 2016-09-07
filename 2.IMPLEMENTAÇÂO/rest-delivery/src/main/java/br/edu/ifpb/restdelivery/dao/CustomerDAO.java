package br.edu.ifpb.restdelivery.dao;

import br.edu.ifpb.restdelivery.entities.Customer;

/**
 * Interface DAO para Cliente
 * 
 * @author rafaelfeitosa - <a href="https://github.com/JoseRafael97 ></a>
 *

 */

public interface CustomerDAO extends GenericDAO<Customer, Long> {

	/**
	 * Método que consulta o número de clientes cadastrados.
	 * @return - número de clientes.
	 */
	public Long countAll();

}
