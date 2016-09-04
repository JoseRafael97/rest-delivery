package br.edu.ifpb.restdelivery.dao;

import java.util.List;

import br.edu.ifpb.restdelivery.entities.Address;
/**
 * Interface DAO para Endereço
 * @author rafaelfeitosa - <a href="https://github.com/JoseRafael97 ></a>
 * 

 */
public interface AddressDAO extends GenericDAO<Address, Long>{
	
	/**
	 * Busca todas as cidades de acordo com id do estado passado como parâmetro.
	 * @param idState
	 * @return Lista de cidades.
	 */
	public List<String> loadingCity(int idState);
	
	/**
	 * Buscar endereços com nome da rua passado como parâmetro.
	 * @param street
	 * @return lista de endereços com a mesma rua, se existir.
	 */
	public List<Address> findByStreet(String street);

}
