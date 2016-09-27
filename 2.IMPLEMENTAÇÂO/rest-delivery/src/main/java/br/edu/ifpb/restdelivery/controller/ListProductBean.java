package br.edu.ifpb.restdelivery.controller;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.restdelivery.entities.Product;
import br.edu.ifpb.restdelivery.exceptions.RestDeliveryException;
import br.edu.ifpb.restdelivery.services.impl.ProductService;

/**
 * Classe controller lista de produtos
 * @author rafaelfeitosa - <a href="https://github.com/JoseRafael97 ></a>
 *
 */
@Named
@RequestScoped
public class ListProductBean extends AbstractBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private ProductService service;
	
	private List<Product> products;
	
	/**
	 * Recupera lista de produtos.
	 * @return
	 * @throws RestDeliveryException 
	 */
	public List<Product> getProducts() throws RestDeliveryException {
		if (products == null) {
			products = (List<Product>) service.listAll();
		}
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

}
