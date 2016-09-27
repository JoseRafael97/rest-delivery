package br.edu.ifpb.restdelivery.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.FlowEvent;

import br.edu.ifpb.restdelivery.entities.Address;
import br.edu.ifpb.restdelivery.entities.Customer;
import br.edu.ifpb.restdelivery.entities.Delivery;
import br.edu.ifpb.restdelivery.entities.ItemProduct;
import br.edu.ifpb.restdelivery.entities.Order;
import br.edu.ifpb.restdelivery.entities.Payment;
import br.edu.ifpb.restdelivery.enumerations.TypesCartCreditFlag;
import br.edu.ifpb.restdelivery.exceptions.RestDeliveryException;
import br.edu.ifpb.restdelivery.services.impl.CustomerService;
import br.edu.ifpb.restdelivery.util.tools.Tools;
import br.edu.ifpb.restdelivery.validators.ValidatorCustomer;

/**
 * 
 * Classe controle para operações de finalização de pedido
 * @author rafaelfeitosa - <a href="https://github.com/JoseRafael97 ></a>
 *
 */
@Named
@ViewScoped
public class FinallyOrderBean extends AbstractBean {

	private static final long serialVersionUID = 1L;

	private Customer customer;
	private Order order;
	
	private List<ItemProduct> selected;

	@Inject
	private CustomerService clientService;

	/**
	 * Método que inicializar atributos.
	 */
	@PostConstruct
	public void init() {

		if (order == null) {
			order = new Order();
			order.setPayment(new Payment());
			order.setCustomer(new Customer());
			Delivery delivery = new Delivery();
			delivery.setAddress(new Address());
			order.setDelivery(delivery);

		}
		
		selected = new ArrayList<>();

	}

	/**
	 * Método implementado para utilizar next do wizard do primefaces.
	 * @param event
	 * @return
	 */
	public String onFlowProcess(FlowEvent event) {
		return event.getNewStep();
	}

	/**
	 * Método que finaliza uma compra criando um novo pedido vincualando a um cliente.
	 * @return
	 * @throws RestDeliveryException
	 */
	public String finallybuy() throws RestDeliveryException {

		Address address = customer.getAddress();

		
		if (!address.getCity().equals(order.getDelivery().getAddress().getCity())
				|| !address.getNumber().equals(order.getDelivery().getAddress().getNumber())) {
			
			order.getDelivery().getAddress().setId(null);
			customer.setAddress(order.getDelivery().getAddress());
		}

		ValidatorCustomer.validateBuy(customer);
		order.setData(new Date());
		order.setTotalprice(Tools.priceAllItens(order.getItemProducts()));
		order.getPayment().setData(new Date());
		customer.getOrders().add(order);
		order.setCustomer(customer);

		clientService.update(customer);

		reportSuccessMensage("Seu pedido foi registrado");
		return "main_page?faces-redirect=true";
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * Recuperar bandeiras de cartão de crédito do enum.
	 * @return
	 */
	public TypesCartCreditFlag[] getTypesCartCreditFlag() {
		return TypesCartCreditFlag.values();
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public List<ItemProduct> getSelected() {
		return selected;
	}

	public void setSelected(List<ItemProduct> selected) {
		this.selected = selected;
	}

	
}
