package br.edu.ifpb.restdelivery.validators;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifpb.restdelivery.entities.Customer;
import br.edu.ifpb.restdelivery.entities.Order;

/**
 * Classe que contém alguns validade de Customer
 * 
 * @author rafaelfeitosa - <a href="https://github.com/JoseRafael97 ></a>
 *
 */
public class ValidatorCustomer extends ValidatorPerson {

	/**
	 * Validador para cliente, que virifica se o cliente não tem pedido e se não
	 * tiver cria uma nova lista de pedidos vazia para evitar
	 * nullpoiterexeception
	 * 
	 * @param client
	 */
	public static void validateBuy(Customer client) {
		if (client.getOrders() == null) {
			List<Order> orders = new ArrayList<>();
			client.setOrders(orders);
		}
	}

}
