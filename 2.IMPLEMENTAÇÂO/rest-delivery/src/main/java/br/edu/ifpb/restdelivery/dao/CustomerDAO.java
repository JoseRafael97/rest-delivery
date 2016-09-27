package br.edu.ifpb.restdelivery.dao;

import java.util.Date;
import java.util.Map;

import br.edu.ifpb.restdelivery.entities.Customer;

/**
 * Interface DAO para Cliente
 * 
 * @author rafaelfeitosa - <a href="https://github.com/JoseRafael97 ></a>
 *
 * 
 */

public interface CustomerDAO extends GenericDAO<Customer, Long> {

	/**
	 * Método que consulta o número de clientes cadastrados.
	 * 
	 * @return - número de clientes.
	 */
	public Long countAll();

	/**
	 * Busca clientes cadastrados na loja por data
	 * 
	 * @param numberDays
	 * @return
	 */
	public Map<Date, Long> findCustomerToDate(Integer numberDays);

	/**
	 * Busca clientes cadastrados na loja por data e sexo
	 * @param numberDays
	 * @param sexo
	 * @return
	 */
	public Map<Date, Long> findCustomerToDateToSexo(Integer numberDays, String sexo);
}
