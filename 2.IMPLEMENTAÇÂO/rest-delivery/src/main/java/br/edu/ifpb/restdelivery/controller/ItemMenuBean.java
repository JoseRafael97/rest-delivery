package br.edu.ifpb.restdelivery.controller;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.restdelivery.entities.ItemMenu;
import br.edu.ifpb.restdelivery.services.impl.ProductService;
import br.edu.ifpb.restdelivery.util.tools.ToolsProduct;

/**
 * Classe controller para ItemMenu 
 * @author rafaelfeitosa - <a href="https://github.com/JoseRafael97 ></a>
 *
 */
@Named
@ViewScoped
public class ItemMenuBean extends AbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ItemMenu itemMenu;
	
	@Inject
	private ProductService productService;
	
	private Integer number;

	/**
	 * Inicia os atributos
	 */
	public void preRendererView() {
		if (itemMenu == null) {
			itemMenu = new ItemMenu();
		}
	}

	public ItemMenu getItemMenu() {
		if (itemMenu != null) {
			ToolsProduct.loadImgItemMenu(itemMenu);
		}

		return itemMenu;
	}

	public void setItemMenu(ItemMenu itemMenu) {
		this.itemMenu = itemMenu;
	}

	/**
	 * Consultar as media de item do menu
	 * @param item
	 * @return
	 */
	public Integer getAverageNumber(ItemMenu item) {
		if(item != null && item.getProduct().getRatingProducts().size()>0){
			number = productService.averageProductRating(item.getProduct().getName()).intValue();
		}else{
			number = 0;
		}
		return number;
	}

}
