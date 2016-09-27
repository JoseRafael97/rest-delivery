package br.edu.ifpb.restdelivery.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.RowEditEvent;

import br.edu.ifpb.restdelivery.entities.Order;
import br.edu.ifpb.restdelivery.exceptions.RestDeliveryException;
import br.edu.ifpb.restdelivery.services.impl.OrderService;

/**
 * Classe controller 
 * @author rafaelfeitosa - <a href="https://github.com/JoseRafael97 ></a>
 *
 */
@Named
@ViewScoped
public class ListOrderBean extends AbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private OrderService orderService;

	private List<Order> orders;
	
	@PostConstruct
	public void init(){
		orders = new ArrayList<>();
	}

	/**
	 * Recupera a lista de pedidos
	 * @return
	 * @throws RestDeliveryException 
	 */
	public List<Order> getOrders() throws RestDeliveryException {
		if (orders == null || orders.isEmpty()) {
			orders = orderService.listAll();
		}
		return orders;
	}
	
	/**
	 * Implementação do método de editar do primefaces
	 * @param event
	 * @throws RestDeliveryException
	 */
	public void onRowEdit(RowEditEvent event) throws RestDeliveryException {
		Order order = (Order) event.getObject();
				
		for (Order or : orders) {
			if (or.getId() == order.getId()) {
				or.getDelivery().setState(order.getDelivery().getState());
				or.getDelivery().setDate(new Date());
				orderService.update(or);

			}
		}
		reportSuccessMensage("Editado com sucesso!");
	}
		
	
	/**
	 * Implementação do método de cancelar edição do primefaces.
	 * @param event
	 */
	public void onRowCancel(RowEditEvent event) {
		reportSuccessMensage("Editar cancelado!");
	}


	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	

}
