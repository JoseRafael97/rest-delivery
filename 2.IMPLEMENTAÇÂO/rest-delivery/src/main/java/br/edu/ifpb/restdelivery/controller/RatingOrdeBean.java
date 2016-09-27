package br.edu.ifpb.restdelivery.controller;

import java.util.Date;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import br.edu.ifpb.restdelivery.entities.Order;
import br.edu.ifpb.restdelivery.entities.RatingOrder;
import br.edu.ifpb.restdelivery.entities.RatingProduct;
import br.edu.ifpb.restdelivery.exceptions.RestDeliveryException;
import br.edu.ifpb.restdelivery.services.impl.OrderService;
import br.edu.ifpb.restdelivery.services.impl.ProductService;
import br.edu.ifpb.restdelivery.util.tools.Tools;
/**
 * Classe Controller RatingOrder
 * @author rafaelfeitosa - <a href="https://github.com/JoseRafael97 ></a>
 *
 */
@Named
@ViewScoped
public class RatingOrdeBean extends AbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private OrderService orderService;
	
	@Inject
	private ProductService productService;

	private Order order;

	private RatingProduct ratingProduct;
	private RatingOrder ratingOrder;
	

	/**
	 * Inicializa atributos 
	 */
	public void preRenderView() {

		if (ratingOrder == null) {
			ratingOrder = new RatingOrder();
		}
	
		if (order == null) {
			order = new Order();
			order.setRatingOrder(ratingOrder);
		}

		if (ratingProduct == null) {
			ratingProduct = new RatingProduct();
			ratingProduct.setDate(new Date());
		}
	}

	/**
	 * Atualizar pedido com avaliação.
	 * @return
	 * @throws RestDeliveryException
	 */
	public String save() throws RestDeliveryException {
		
		ratingOrder.setDate(new Date());
		order.setRatingOrder(ratingOrder);
		
		orderService.update(order);
		reportSuccessMensage("Avaliação registrada");
		return "/pages/customer/settings_count_user?faces-redirect=true";

	}
	
	/**
	 * Atualiza produto com avaliação.
	 * @throws RestDeliveryException
	 */
	public void saveRatingProduct() throws RestDeliveryException{
		ratingProduct.setUserNameCustomer(Tools.recoversUserName());
		productService.update(ratingProduct.getProduct());
		reportSuccessMensage("Sua avaliação foi registrada com sucesso.");		
		
		ratingProduct = new RatingProduct();
		ratingProduct.setDate(new Date());
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public RatingProduct getRatingProduct() {
		return ratingProduct;
	}

	public void setRatingProduct(RatingProduct ratingProduct) {
		this.ratingProduct = ratingProduct;
	}

	public RatingOrder getRatingOrder() {
		return ratingOrder;
	}

	public void setRatingOrder(RatingOrder ratingOrder) {
		this.ratingOrder = ratingOrder;
	}

	
}
