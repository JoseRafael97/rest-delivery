package br.edu.ifpb.restdelivery.validators;

import java.util.List;
import br.edu.ifpb.restdelivery.entities.ItemMenu;
import br.edu.ifpb.restdelivery.util.jsf.FacesUtil;

/**
 * Classe que contém alguns validadores para Menu
 * 
 * @author rafaelfeitosa - <a href="https://github.com/JoseRafael97 ></a>
 *
 */
public class ValidatorMenu {

	/**
	 * Método que verifica se campos de menu estão validos.
	 * @param item
	 * @param itensSelected
	 * @return
	 */
	public static boolean validateItemMenu(ItemMenu item, List<ItemMenu> itensSelected) {

		if (item == null) {
			FacesUtil.addErrorMessage("Por favor preencha os campos!");
			return true;
		}
		
		if (item != null && item.getProduct() == null) {
			FacesUtil.addErrorMessage("Por favor, Selecione um item");
			return true;
		}

		if (item != null && item.getPrice() < 0) {
			FacesUtil.addErrorMessage("Preço inválido!");
			return true;
		}

		if (itensSelected != null && itensSelected.size() > 0) {
			for (int i = 0; i < itensSelected.size(); i++) {
				if (itensSelected.get(i).getProduct().getId() == item.getProduct().getId()) {
					FacesUtil.addErrorMessage("Item do cardápio já adicionado anteriomente");
					return true;

				}
			}

		}
		return false;

	}

}
