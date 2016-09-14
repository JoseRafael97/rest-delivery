package br.edu.ifpb.restdelivery.dao;

import java.util.List;

import br.edu.ifpb.restdelivery.entities.ItemMenu;
import br.edu.ifpb.restdelivery.exceptions.RestDeliveryPersistenceException;
import br.edu.ifpb.restdelivery.util.filters.FilterItemMenu;

/**
 * Interface DAO para Item do Cadárpio
 * @author rafaelfeitosa - <a href="https://github.com/JoseRafael97 ></a>
 *
 */
public interface ItemMenuDAO extends GenericDAO<ItemMenu, Long>{
	
	/**
	 * Filtra os itens de menu que possui menu ativo
	 * @return
	 */
	public List<ItemMenu> getItemMenuWithMenuThan(FilterItemMenu filter)  throws RestDeliveryPersistenceException ;

	
	/**
	 * Conta o número itens filtrados.
	 * @param filter
	 * @return
	 */
	public int countItemMenuFilters(FilterItemMenu filter);
}
