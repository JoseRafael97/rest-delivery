package br.edu.ifpb.restdelivery.services.impl;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import br.edu.ifpb.restdelivery.dao.MenuDAO;
import br.edu.ifpb.restdelivery.entities.Menu;
import br.edu.ifpb.restdelivery.exceptions.RestDeliveryException;
import br.edu.ifpb.restdelivery.util.jpa.Transactional;

/**
 * Classe que fornece serviço da entidade Menu
 * 
 * @author rafaelfeitosa - <a href="https://github.com/JoseRafael97 ></a>
 *
 */
public class MenuService extends ImplGenericService<Menu, Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * * * Construtor injetado com CDI para utilizar o DAO generico, nele
	 * passamos o tipo do dao genérico de Menu.
	 * 
	 * @param menuDAO
	 */
	@Inject
	public MenuService(MenuDAO menuDAO) {
		this.dao = menuDAO;
	}

	/**
	 * Método que busca por um menu ativo.
	 * 
	 * @return
	 * @throws RestDeliveryException
	 */
	public Menu selectMenuActive() throws RestDeliveryException {
		return ((MenuDAO) this.dao).findByMenuActive();
	}

	/**
	 * Método que verifica se existe um menu ativo e retorna uma exeception se
	 * já existir, se não salva o menu ativo passado como parâmetro
	 * 
	 * @param menu
	 * @throws RestDeliveryException
	 */
	public void activeMenu(Menu menu) throws RestDeliveryException {

		if (selectMenuActive() != null) {
			throw new RestDeliveryException("Já estar ativo um cardápio.");
		}

		menu.setAtivo(true);
		this.save(menu);
	}

	@Transactional
	public int addMenu(Menu menu) throws RestDeliveryException {
		Menu menu2 = selectMenuActive();

		if ((menu2 != null && menu.getId() == null && menu.getAtivo())
				|| (menu2 != null && menu.getId() != null && menu2.getId() != menu.getId() && menu.getAtivo())) {
			throw new RestDeliveryException("Já estar ativo um cardápio.");

		}

		if (menu.getId() == null) {
			this.save(menu);
			return 1;
		}
		this.update(menu);
		return 2;
	}
	
	public List<Menu> listAll() throws RestDeliveryException {
		List<Menu> menus = super.listAll();
		for(Menu m : menus){
			if(m.getAtivo()){
				if(m.getDate().before(new Date())){
					m.setAtivo(false);
					update(m);
				}
			}
		}
	
		
		return menus;
	}

	/**
	 * Método que busca um produto do menu pelo seu id.
	 * 
	 * @param id
	 * @return
	 */
	public Long findByProductMenu(Long id) {
		return ((MenuDAO) this.dao).findByProductMenu(id);
	}

	/**
	 * Método que conta todos os menu cadastrados.
	 * 
	 * @return
	 */
	public Long countAll() {
		return ((MenuDAO) this.dao).countAll();
	}

}
