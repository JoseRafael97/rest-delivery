package br.edu.ifpb.restdelivery.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.primefaces.event.RowEditEvent;

import br.edu.ifpb.restdelivery.entities.ItemMenu;
import br.edu.ifpb.restdelivery.entities.ItemProduct;
import br.edu.ifpb.restdelivery.util.jsf.FacesUtil;
import br.edu.ifpb.restdelivery.util.tools.Tools;
/**
 * Controlle do carrinho de compra do cliente.
 * 
 * @author rafaelfeitosa - <a href="https://github.com/JoseRafael97 ></a>
 *
 */
@Named
@SessionScoped
public class CustomerKartBean extends AbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<ItemProduct> itemProducts;
	private ItemProduct itemProduct;
	private ItemMenu itemMenu;
	

	@PostConstruct
	void init() {
		itemProduct = new ItemProduct();
		itemProducts = new ArrayList<>();
	}

	/**
	 * Método que salva um item de produto numa lista que representa o carrinho do cliente.
	 */
	public void saveItemProduct() {

		if (itemProduct.getAmount() == null || itemProduct.getAmount() < 1) {
			FacesUtil.addErrorMessage("Quantidade inválida");
			return;
		}

		if (itemProducts != null && itemProducts.size() > 0) {
			for (int i = 0; i < itemProducts.size(); i++) {
				if (itemProducts.get(i).getItemMenu().getId() == itemMenu.getId()) {
					FacesUtil.addErrorMessage("Item já adicionado anteriomente, Acesse o carrinho para mais detalhes");
					return;
				}
			}

		}

		itemProduct.setItemMenu(itemMenu);
		itemProduct.setPrice(Tools.priceItem(itemProduct.getAmount(), itemMenu.getPrice()));
		itemProducts.add(itemProduct);
		itemMenu = new ItemMenu();
		itemProduct = new ItemProduct();
		reportSuccessMensage("Item adicionado no seu carrinho de compras!");

	}

	/**
	 * Método que remove  um item do carrinho
	 */
	public void removeItemProduct() {
		itemProducts.remove(itemProduct);
	}

	/**
	 * Método para editar a quantidade de um item do carrinho de compras do cliente.
	 * @param event
	 */
	public void onRowEdit(RowEditEvent event) {
		ItemProduct item = (ItemProduct) event.getObject();
		for (ItemProduct ip : itemProducts) {
			if (ip.getId() == item.getId()) {
				ip.setAmount(item.getAmount());
				ip.setPrice(Tools.priceItem(item.getAmount(), item.getItemMenu().getPrice()));
			}
		}
		reportSuccessMensage("Editado com sucesso!");
	}

	public String cancelOrder() {
		itemProducts.clear();
		return "main_page?faces-redirect=true";

	}

	/**
	 * Método número de itens
	 * @return
	 */
	public Integer numberItens() {
		if (itemProducts == null) {
			return 0;
		}

		return itemProducts.size();
	}

	/**
	 * Método que calcula o preço total.
	 * @return
	 */
	public Float getPriceAll() {
		return Tools.priceAllItens(itemProducts);
	}

	public void onRowCancel(RowEditEvent event) {
		reportSuccessMensage("Editar cancelado!");
	}

	public List<ItemProduct> getItemProducts() {
		return itemProducts;
	}

	public void setItemProducts(List<ItemProduct> itemProducts) {
		this.itemProducts = itemProducts;
	}

	public ItemProduct getItemProduct() {
		return itemProduct;
	}

	public void setItemProduct(ItemProduct itemProduct) {
		this.itemProduct = itemProduct;
	}

	public ItemMenu getItemMenu() {
		return itemMenu;
	}

	public void setItemMenu(ItemMenu itemMenu) {
		this.itemMenu = itemMenu;
	}

}
