package br.edu.ifpb.restdelivery.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.edu.ifpb.restdelivery.entities.Address;
import br.edu.ifpb.restdelivery.entities.Customer;
import br.edu.ifpb.restdelivery.entities.Order;
import br.edu.ifpb.restdelivery.entities.User;
import br.edu.ifpb.restdelivery.enumerations.Grupo;
import br.edu.ifpb.restdelivery.exceptions.RestDeliveryException;
import br.edu.ifpb.restdelivery.services.impl.CustomerService;
import br.edu.ifpb.restdelivery.services.impl.PersonService;
import br.edu.ifpb.restdelivery.services.impl.UserService;
import br.edu.ifpb.restdelivery.util.tools.Tools;
import br.edu.ifpb.restdelivery.validators.ValidatorPerson;

/**
 * Conotroller para Customer
 * 
 * @author rafaelfeitosa - <a href="https://github.com/JoseRafael97 ></a>
 *
 */
@Named
@ViewScoped
public class CustomerBean extends AbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Customer customer;

	private Address address;
	private User user;

	@Inject
	private CustomerService clientService;

	@Inject
	private UserService userService;

	@Inject
	private PersonService personService;

	public void init() {

		if (customer == null) {
			customer = new Customer();
			User user = new User();
			user.setGrupo(Grupo.CLIENTE);
			customer.setUser(user);
			customer.setAddress(new Address());
		}

	}

	/**
	 * Método que salva ou atualiza um cliente
	 * 
	 * @throws RestDeliveryException
	 */
	public void save() throws RestDeliveryException {
		customer.getUser().setPerson(customer);
		customer.setAddress(address);
		customer.getUser().setPassword(Tools.criptografarSenha(customer.getUser().getPassword()));

		ValidatorPerson.validatePerson(customer, userService, personService);
		if (customer.getId() == null) {
			customer.setDate(new Date());
			clientService.save(customer);
			reportSuccessMensage("Conta criada!");

		} else {
			clientService.update(customer);
			reportSuccessMensage("Conta atualizada!");
		}

		customer = new Customer();

	}

	/**
	 * Remove o cliente do sistema
	 * 
	 * @param customer
	 * @throws RestDeliveryException
	 */
	public String remove(Customer customer) throws RestDeliveryException {

		clientService.removeCountCustomer(customer);
		
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.invalidate();

		return "/pages/index?faces-redirect=true";

	}

	/**
	 * Método que recupera os pedidos realizados pelo cliente logado.
	 * 
	 * @return
	 */
	public List<Order> listOrderCustomer() {
		if (getUser() != null && getUser().getGrupo().equals(Grupo.CLIENTE)) {
			customer = (Customer) getUser().getPerson();

			return customer.getOrders();
		}

		return new ArrayList<>();
	}

	/**
	 * método que recupera o usuário logado.
	 * 
	 * @return
	 */
	private User getUser() {

		String userName = Tools.recoversUserName();
		if (user == null && userName != null) {
			return userService.findByName(userName);
		}
		user = new User();
		return user;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * Recupera o número de clientes cadastrados
	 * 
	 * @return
	 */
	public String getNumberItens() {
		String value = "0";
		if (clientService.countAll() < 10) {
			value = "0" + clientService.countAll().toString();
		} else {
			value = clientService.countAll().toString();
		}
		return value;
	}

}
