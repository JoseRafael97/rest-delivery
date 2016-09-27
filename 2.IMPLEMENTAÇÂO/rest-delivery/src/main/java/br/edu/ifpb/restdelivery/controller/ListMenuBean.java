package br.edu.ifpb.restdelivery.controller;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.restdelivery.entities.ItemMenu;
import br.edu.ifpb.restdelivery.entities.Menu;
import br.edu.ifpb.restdelivery.exceptions.RestDeliveryException;
import br.edu.ifpb.restdelivery.services.impl.MenuService;

/**
 * Classe controller para lista de menu 
 * @author rafaelfeitosa - <a href="https://github.com/JoseRafael97 ></a>
 *
 */
@Named
@RequestScoped
public class ListMenuBean extends AbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Menu menu;

	private List<Menu> menus;

	@Inject
	private MenuService menuService;

	public Menu getMenu() {
		return menu;
	}

	/**
	 * Remove um menu da lista de menu
	 * @throws RestDeliveryException
	 */
	public void remove() throws RestDeliveryException {
		for(ItemMenu itemMenu : menu.getMenuItens()){
			itemMenu.setMenu(null);
		}
		menuService.update(menu);
		menuService.remove(menu);
		menus.remove(menu);
		reportSuccessMensage("Cardápio removido!");
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	/**
	 * Recuperar os menus existentes.
	 * @return
	 * @throws RestDeliveryException 
	 */
	public List<Menu> getMenus() throws RestDeliveryException {
		if (menus == null) {
			menus = menuService.listAll();
		}
		return menus;

	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}

}
