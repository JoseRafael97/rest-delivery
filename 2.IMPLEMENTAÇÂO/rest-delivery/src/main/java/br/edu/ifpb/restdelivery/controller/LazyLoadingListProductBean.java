package br.edu.ifpb.restdelivery.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.edu.ifpb.restdelivery.entities.Product;
import br.edu.ifpb.restdelivery.exceptions.RestDeliveryException;
import br.edu.ifpb.restdelivery.services.impl.ProductService;
import br.edu.ifpb.restdelivery.util.filters.FilterProducts;
import br.edu.ifpb.restdelivery.util.tools.ToolsProduct;

/**
 * Classe controller lazy loading  product
 * @author rafaelfeitosa - <a href="https://github.com/JoseRafael97 ></a>
 *
 */
@Named
@ViewScoped
public class LazyLoadingListProductBean extends AbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Product product;

	private LazyDataModel<Product> model;

	private FilterProducts filter;

	@Inject
	private ProductService service;

	/**
	 * Incia atributos.
	 */
	@PostConstruct
	void init() {
		product = new Product();
		filter = new FilterProducts();
	}

	/**
	 * Metodo contrustor para setar listar na tabela
	 */
	public LazyLoadingListProductBean() {
		model = new LazyDataModel<Product>() {

			private static final long serialVersionUID = 1L;

			@Override
			public List<Product> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {

				filter.setFirst(first);
				filter.setAmount(pageSize);
				filter.setAscendent(SortOrder.ASCENDING.equals(sortOrder));
				filter.setPropetyOrdened(sortField);

				setRowCount(service.countProductFilters(filter));
				ToolsProduct.loadImgProduct(service.filterProduct(filter));
				return service.filterProduct(filter);
			}

		};
	}

	/**
	 * Remove um produto do sistema.
	 * @param p
	 */
	public void remove(Product p) {
		try {
			service.removeProduct(p);
			reportSuccessMensage("Item de menu removido!");
		} catch (RestDeliveryException e) {
			reportErroMensage(e.getMessage());
			e.printStackTrace();
		}
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public LazyDataModel<Product> getModel() {
		return model;
	}

	public FilterProducts getFilter() {
		return filter;
	}

	public void setFilter(FilterProducts filter) {
		this.filter = filter;
	}

}