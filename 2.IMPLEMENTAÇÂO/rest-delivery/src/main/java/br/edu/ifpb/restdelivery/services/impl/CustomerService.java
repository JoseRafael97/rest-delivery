package br.edu.ifpb.restdelivery.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import br.edu.ifpb.restdelivery.dao.CustomerDAO;
import br.edu.ifpb.restdelivery.entities.Customer;
import br.edu.ifpb.restdelivery.entities.Order;
import br.edu.ifpb.restdelivery.entities.User;
import br.edu.ifpb.restdelivery.exceptions.RestDeliveryException;
import br.edu.ifpb.restdelivery.util.jpa.Transactional;
import br.edu.ifpb.restdelivery.util.tools.Tools;

public class CustomerService extends ImplGenericService<Customer, Long> {

	@Inject
	private UserService userService;

	private static final long serialVersionUID = 1L;

	/**
	 * * Construtor injetado com CDI para utilizar o DAO generico, nele passamos
	 * o tipo do dao genérico de Customer.
	 * 
	 * @param customerDAO
	 */
	@Inject
	public CustomerService(CustomerDAO customerDAO) {
		this.dao = customerDAO;
	}

	@Transactional
	public void removeCountCustomer(Customer customer) throws RestDeliveryException {

		List<Order> orders = customer.getOrders();

		if (orders != null && orders.size() > 0) {
			User user = userService.findByName(Tools.recoversUserName());
			customer.setUser(null);
			this.update(customer);
			userService.remove(user);

		} else {
			this.remove(customer);
		}
	}

	/**
	 * Método que busca o número de clientes cadastrados.
	 * 
	 * @return
	 */
	public Long countAll() {
		return ((CustomerDAO) dao).countAll();
	}

	public Map<Date, Long> findCustomerToDate(Integer numberDays) {
		return ((CustomerDAO) dao).findCustomerToDate(numberDays);
	}

	public Map<Date, Long> findCustomerToDateToSexo(Integer numberDays, String sexo) {
		return ((CustomerDAO) dao).findCustomerToDateToSexo(numberDays, sexo);
	}

}
