package br.edu.ifpb.restdelivery.validators;

import java.util.List;

import br.edu.ifpb.restdelivery.entities.ItemMenu;
import br.edu.ifpb.restdelivery.entities.ItemProduct;
import br.edu.ifpb.restdelivery.entities.Product;
import br.edu.ifpb.restdelivery.exceptions.RestDeliveryPersistenceException;
import br.edu.ifpb.restdelivery.services.impl.MenuService;
import br.edu.ifpb.restdelivery.util.jsf.FacesUtil;

/**
 * Classe que contém métodos validadores para Product.
 * 
 * @author rafaelfeitosa - <a href="https://github.com/JoseRafael97 ></a>
 *
 */
public class ValidatorProduct {

	/**
	 * Verifica se o produto a ser removido não está sendo utilizado.
	 * 
	 * @param p
	 * @param service
	 * @throws RestDeliveryPersistenceException
	 */

	public static void validateRemoveProduct(Product p, MenuService service) throws RestDeliveryPersistenceException {

		if (service.findByProductMenu(p.getId()) != null) {
			throw new RestDeliveryPersistenceException(
					"Item de cardápio não pode ser removido, Verifique não estar em uso em algum cardápio.");
		}

	}

	/**
	 *Valida adicionamento do mesmo no carrinho verificando se informou uma quantidade válida e se o item já não está no carrinho.
	 * @param itemProducts
	 * @param itemProduct
	 * @param itemMenu
	 */
	public static void validateAddProductItem(List<ItemProduct> itemProducts, ItemProduct itemProduct,
			ItemMenu itemMenu) {

		if (itemProduct.getAmount() == null || itemProduct.getAmount() < 1) {
			FacesUtil.addErrorMessage("Quantidade inválida");
			return;
		}

		if (itemProducts != null && itemProducts.size() > 0) {
			for (int i = 0; i < itemProducts.size(); i++) {
				if (itemProducts.get(i).getItemMenu().getId() == itemMenu.getId()) {
					FacesUtil.addErrorMessage("Item já adicionado anteriomente.");
					return;
				}
			}

		}

	}

}
