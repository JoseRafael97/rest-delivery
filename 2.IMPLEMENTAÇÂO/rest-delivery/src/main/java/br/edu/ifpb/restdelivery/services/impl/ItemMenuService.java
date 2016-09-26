package br.edu.ifpb.restdelivery.services.impl;

import java.util.List;

import javax.inject.Inject;

import br.edu.ifpb.restdelivery.dao.ItemMenuDAO;
import br.edu.ifpb.restdelivery.entities.ItemMenu;
import br.edu.ifpb.restdelivery.exceptions.RestDeliveryPersistenceException;
import br.edu.ifpb.restdelivery.util.filters.FilterItemMenu;

/**
 * Classe que provê serviços da para entidade ItemMenu
 * 
 * @author rafaelfeitosa - <a href="https://github.com/JoseRafael97 ></a>
 *
 */
public class ItemMenuService extends ImplGenericService<ItemMenu, Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 	 * * Construtor injetado com CDI para utilizar o DAO generico, nele passamos
	 * o tipo do dao genérico de ItemMenu.
	 * @param itemMenuDAO
	 */
	@Inject
	public ItemMenuService(ItemMenuDAO itemMenuDAO) {
		this.dao = itemMenuDAO;
	}
	
	/**
	 * Método que busca com filtros por itens de menu.
	 * @param filter
	 * @return
	 * @throws RestDeliveryPersistenceException
	 */
	public List<ItemMenu> getItemMenuWithMenuThan(FilterItemMenu filter) throws RestDeliveryPersistenceException{
		return ((ItemMenuDAO)dao).getItemMenuWithMenuThan(filter);
	}
	
	/**
	 * Método que busca a quantidade de itens filtratos.
	 * @param filter
	 * @return
	 */
	public int countItemMenuFilters(FilterItemMenu filter){
		return ((ItemMenuDAO)dao).countItemMenuFilters(filter);
	}

}
