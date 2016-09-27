package br.edu.ifpb.restdelivery.controller;

import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.restdelivery.entities.Order;
import br.edu.ifpb.restdelivery.services.impl.OrderService;

/**
 * Classe controller Para Order.
 * @author rafaelfeitosa - <a href="https://github.com/JoseRafael97 ></a>
 *
 */
@Named
@ViewScoped
public class OrderBean extends AbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private OrderService service;
	
	private List<Order> orders;
	
	private Order order;
	
	public void init(){
		if (order == null) {
			order = new Order();
		}
	}

	/**
	 * recupera o numero de itens total cadastrados.
	 * @return
	 */
	public String getNumberItens() {
		String value = "0";
		if (service.countAll() < 10) {
			value = "0" + service.countAll().toString();
		} else {
			value = service.countAll().toString();
		}
		return value;
	}

	/**
	 * Recupera as avaliações dos pedidos
	 * @return
	 */
	public String getNumberItensRating() {
		String value = "0";
		if (service.countRatingAll() < 10) {
			value = "0" + service.countRatingAll().toString();
		} else {
			value = service.countRatingAll().toString();
		}
		return value;
	}

	/**
	 * Valida botão Rating
	 * @param order
	 * @return
	 */
	public boolean validateBtnRating(Order order) {
		if(order.getRatingOrder() == null || order.getDelivery().getState()){
			return true;
		}
		
		return false;
	}
	
	/**
	 *Recupera os pedidos do banco 
	 * @return
	 */
	public List<Order> getOrders() {
		if(orders == null){
			orders = service.findOrderToRating();
		}
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	
	
	
	
}
