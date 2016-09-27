package br.edu.ifpb.restdelivery.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.edu.ifpb.restdelivery.entities.ItemMenu;
import br.edu.ifpb.restdelivery.enumerations.OptionFind;
import br.edu.ifpb.restdelivery.exceptions.RestDeliveryPersistenceException;
import br.edu.ifpb.restdelivery.services.impl.ItemMenuService;
import br.edu.ifpb.restdelivery.util.filters.FilterItemMenu;
import br.edu.ifpb.restdelivery.util.tools.ToolsProduct;

/**
 * Classe controller Cardápio de usuário
 * @author rafaelfeitosa - <a href="https://github.com/JoseRafael97 ></a>
 *
 */
@Named
@ViewScoped
public class MenuUserBean extends AbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private ItemMenuService itemMenuService;


	private LazyDataModel<ItemMenu> model;

	private FilterItemMenu filter = new FilterItemMenu();
	
	private Float priceMin = 0f;
	private Float priceMax = 100f;
	
	/**
	 * Método construtor que inicializa lista itens de menu do menu ativo.
	 */
	public MenuUserBean() {
		model = new LazyDataModel<ItemMenu>() {

			private static final long serialVersionUID = 1L;

			@Override
			public List<ItemMenu> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {

				filter.setFirst(first);
				filter.setAmount(pageSize);
				filter.setAscendent(SortOrder.ASCENDING.equals(sortOrder));
				filter.setPropetyOrdened(sortField);

				setRowCount(itemMenuService.countItemMenuFilters(filter));
				
				try {
					ToolsProduct.loadImgItens(itemMenuService.getItemMenuWithMenuThan(filter));
				} catch (RestDeliveryPersistenceException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
				try {
					return itemMenuService.getItemMenuWithMenuThan(filter);
				} catch (RestDeliveryPersistenceException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return new ArrayList<>();
			}

		};
	}

	/**
	 * passa e atualiza atributos.
	 * 
	 */
	public void setUpdate(){
		filter.setMinPrice(priceMin);
		filter.setMaxPrice(priceMax);
	
	}
	
	public FilterItemMenu getFilter() {
		return filter;
	}

	public void setFilter(FilterItemMenu filter) {
		this.filter = filter;
	}

	public LazyDataModel<ItemMenu> getModel() {
		return model;
	}

	public void setModel(LazyDataModel<ItemMenu> model) {
		this.model = model;
	}




	public ItemMenuService getItemMenuService() {
		return itemMenuService;
	}




	public void setItemMenuService(ItemMenuService itemMenuService) {
		this.itemMenuService = itemMenuService;
	}




	public Float getPriceMin() {
		return priceMin;
	}




	public void setPriceMin(Float priceMin) {
		this.priceMin = priceMin;
	}


	public OptionFind [] getOpFilterMenu(){
		return OptionFind.values();
	}

	public Float getPriceMax() {
		return priceMax;
	}




	public void setPriceMax(Float priceMax) {
		this.priceMax = priceMax;
	}
	
	

}
