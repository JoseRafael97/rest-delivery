package br.edu.ifpb.restdelivery.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.edu.ifpb.restdelivery.dao.AddressDAO;
import br.edu.ifpb.restdelivery.entities.Address;
/**
 *Implementação de Endereço DAO
 * 
 * @author rafaelfeitosa - <a href="https://github.com/JoseRafael97 ></a>
 *
 */
public class ImplAddressDAO extends ImplGenericDAO<Address, Long> implements AddressDAO,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Método que carrega todas as cidades do estado que pertece o id passado como parâmentro.
	 */
	@SuppressWarnings("unchecked")
	public List<String> loadingCity(int idState) {
		Query query = (Query) em
				.createNativeQuery("Select fd.city from fd_cidades fd where fd.state = " + idState);

		return query.getResultList();
	}

	/**
	 * Método que busca endereços com a nome da rua passado ou semelhante ao nome 
	 */
	@Override
	public List<Address> findByStreet(String street) {
		try {
			TypedQuery<Address> query = em.createNamedQuery("address.findByStreet", Address.class);
			query.setParameter("street", "%"+street+"%");
			return query.getResultList();

		} catch (NoResultException e) {
			return null;
		}
	}

}
