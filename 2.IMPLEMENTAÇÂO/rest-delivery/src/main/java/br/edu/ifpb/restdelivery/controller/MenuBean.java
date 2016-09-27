package br.edu.ifpb.restdelivery.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.RowEditEvent;

import br.edu.ifpb.restdelivery.entities.ItemMenu;
import br.edu.ifpb.restdelivery.entities.Menu;
import br.edu.ifpb.restdelivery.exceptions.RestDeliveryException;
import br.edu.ifpb.restdelivery.services.impl.MenuService;

/**
 * 
 * @author rafaelfeitosa - <a href="https://github.com/JoseRafael97 ></a>
 *
 */
@Named
@ViewScoped
public class MenuBean extends AbstractBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private MenuService menuService;

	private Menu menu;
	private ItemMenu item;
	private Date minDate;

	/**
	 * Inicializa os atributos.
	 */
	public void preRenderView() {
		if (menu == null) {
			menu = new Menu();
			menu.setMenuItens(new ArrayList<>());
		}

		if (item == null) {
			item = new ItemMenu();
		}

		if (minDate == null) {
			minDate = new Date();
		}

	}

	/**
	 * Salva um novo menu no banco.
	 * 
	 * @return
	 * @throws RestDeliveryException
	 */
	public String save() throws RestDeliveryException {

		int i = menuService.addMenu(menu);
		if (i == 2) {
			reportSuccessMensage("Cardápio atualizado.");
			return "/pages/employee/menu";
		}
		return null;
	}

	/**
	 * Método que remove um item do menu
	 * 
	 * @throws RestDeliveryException
	 */
	public void removeItem(ItemMenu itemMenu) {

		menu.getMenuItens().remove(itemMenu);
		reportSuccessMensage("Item do cardápio removido!");
	}

	/**
	 * Método para editar a quantidade de um item da lista do cardápio.
	 * 
	 * @param event
	 */
	public void onRowEdit(RowEditEvent event) {
		ItemMenu item = (ItemMenu) event.getObject();

		for (ItemMenu im : menu.getMenuItens()) {
			if (im.getId() == item.getId()) {
				im.setPrice(item.getPrice());
			}
		}
		reportSuccessMensage("Editado com sucesso.");
	}

	/**
	 * Ação após o cancelamento do evento de editar.
	 * 
	 * @param event
	 */
	public void onRowCancel(RowEditEvent event) {
		reportSuccessMensage("Editar cancelado!");
	}

	/**
	 * Adiciona um item no menu
	 */
	public void addItemMenu() {
		int cont = 0;
		if (item != null) {
			if (menu.getMenuItens() != null && menu.getMenuItens().size() > 0) {
				for (ItemMenu im : menu.getMenuItens()) {
					if (item.getProduct().getName().equals(im.getProduct().getName())) {
						cont++;
						if (cont > 1) {
							menu.getMenuItens().remove(im);
							reportErroMensage("Item já Adicionado");
							return;
						}
					}
				}
			}
		}
		item = new ItemMenu();
		reportSuccessMensage("Item adicionado.");
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public ItemMenu getItem() {

		return item;
	}

	public void setItem(ItemMenu item) {
		this.item = item;
	}

	/**
	 * Recupera o nome itens
	 * 
	 * @return
	 */
	public String getNumberItens() {
		String value = "0";
		if (menuService.countAll() < 10) {
			value = "0" + menuService.countAll().toString();
		} else {
			value = menuService.countAll().toString();
		}
		return value;
	}

	public Date getMinDate() {
		return minDate;
	}

	public void setMinDate(Date minDate) {
		this.minDate = minDate;
	}

}
