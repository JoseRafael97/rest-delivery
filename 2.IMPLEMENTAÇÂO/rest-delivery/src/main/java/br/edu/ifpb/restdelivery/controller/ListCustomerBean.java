package br.edu.ifpb.restdelivery.controller;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.restdelivery.entities.Customer;
import br.edu.ifpb.restdelivery.exceptions.RestDeliveryException;
import br.edu.ifpb.restdelivery.services.impl.CustomerService;

/**
 * Classe controller para lista de clientes
 * @author rafaelfeitosa - <a href="https://github.com/JoseRafael97 ></a>
 *
 */
@Named
@RequestScoped
public class ListCustomerBean extends AbstractBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Customer> customers;
	
	/**
	 * Inicia de atributos
	 */
	public void init(){
		customers = new ArrayList<>();
	}
	
	@Inject
	private CustomerService customerService;

	/**
	 * Retorna lista de clientes existentes.
	 * @return
	 * @throws RestDeliveryException 
	 */
	public List<Customer> getCustomers() throws RestDeliveryException {
		if (customers == null) {
			customers = customerService.listAll();
		}
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}
	
	
	

}
