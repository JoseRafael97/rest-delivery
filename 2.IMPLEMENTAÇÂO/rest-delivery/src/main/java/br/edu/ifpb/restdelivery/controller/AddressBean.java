package br.edu.ifpb.restdelivery.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import br.edu.ifpb.restdelivery.entities.Address;
import br.edu.ifpb.restdelivery.entities.Customer;
import br.edu.ifpb.restdelivery.enumerations.Grupo;
import br.edu.ifpb.restdelivery.enumerations.StateNames;
import br.edu.ifpb.restdelivery.services.impl.AddressService;
import br.edu.ifpb.restdelivery.services.impl.UserService;
import br.edu.ifpb.restdelivery.util.tools.Tools;

/**
 * Classe controle de Endereço
 * 
 * @author rafaelfeitosa - <a href="https://github.com/JoseRafael97 ></a>
 *
 */
@Named
@ViewScoped
public class AddressBean extends AbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Address address;
	private List<String> listCity;
	private Customer customer;

	@Inject
	private AddressService addressService;

	@Inject
	private UserService userService;

	/**
	 * Método para iniciar objetos, utilizado como pré-render-view
	 */
	public void init() {

		if (address == null) {
			address = new Address();
		}

		if (customer == null) {
			if (Tools.recoversUserName() != null
					&& userService.findByName(Tools.recoversUserName()).getGrupo().equals(Grupo.CLIENTE)) {

				customer = (Customer) (userService.findByName(Tools.recoversUserName()).getPerson());
				address = customer.getAddress();
			}
		}

	}

	/**
	 * Método que utilizado pelo ajax para atualizar a lista de cidades quando o
	 * estado muda
	 * 
	 * @param event
	 */
	public void subjectSelectionChanged(final AjaxBehaviorEvent event) {
		listCity = addressService.loadingCity(Tools.getIdState(address.getState()));
	}

	/**
	 * Método que fornece a lista de cidades de um estado recuperado por meio do
	 * objeto address
	 * 
	 * @return
	 */
	public List<String> getLoadingCity() {
		if (listCity == null) {

			if (address.getState() != null) {
				listCity = addressService.loadingCity(Tools.getIdState(address.getState()));
			} else {
				listCity = new ArrayList<>();
			}
		}
		return listCity;
	}

	/**
	 * Método que busca endereços com a rua passada utilizado pelo oncomplete do
	 * primefaces.
	 * 
	 * @param query
	 * @return
	 */
	public List<String> completeAddress(String query) {

		Set<String> streets = new HashSet<String>();

		List<Address> listAddress = addressService.findByStreet(query);

		if (listAddress != null) {

			for (Address ad : listAddress) {
				streets.add(ad.getStreet());
			}
		}
		return new ArrayList<String>(streets);
	}

	/**
	 * Método que ao selecionar um endereço no oncomplete seta alguns valores no
	 * objeto utilizado adrress para reaproveitamento de informações
	 * 
	 * @param street
	 */
	public void onItemSelect(String street) {
		this.address = new Address();
		List<Address> listAddress = addressService.findByStreet(street);
		Address address = listAddress.get(0);
		this.address.setState(address.getState());
		this.address.setCity(address.getCity());
		this.address.setNeighborhood(address.getNeighborhood());
		this.address.setZipCode(address.getZipCode());
		listCity = addressService.loadingCity(Tools.getIdState(address.getState()));
	}

	/**
	 * Método que recupera o nome de todos estados por meio do enum StateNames.
	 * @return
	 */
	public StateNames[] getStateName() {
		return StateNames.values();
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

}
