package br.edu.ifpb.restdelivery.services.impl;

import java.util.List;

import javax.inject.Inject;

import br.edu.ifpb.restdelivery.dao.AddressDAO;
import br.edu.ifpb.restdelivery.entities.Address;

/**
 * Classe de serviço para a entidade Address (Endereço)
 * 
 * @author rafaelfeitosa - <a href="https://github.com/JoseRafael97 ></a>
 *
 */
public class AddressService extends ImplGenericService<Address, Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Construtor injetado com CDI para utilizar o DAO generico, nele passamos o tipo do dao genérico de Addrees.
	 * 
	 * @param addressDAO
	 */
	@Inject
	public AddressService(AddressDAO addressDAO) {
		this.dao = addressDAO;
	}
	
	/**
	 * Método que carrega todas as cidades do estado com indetificador passado como parâmetro
	 * @param idState
	 * @return
	 */
	public List<String> loadingCity(int idState){
		return ((AddressDAO)this.dao).loadingCity(idState);
	}
	
	/**
	 * Método que busca Endereços com nome da rua igual ao passado no parâmetro.
	 * @param street
	 * @return
	 */
	public List<Address> findByStreet(String street ){
		return ((AddressDAO)this.dao).findByStreet(street);
	}
	
	

}
