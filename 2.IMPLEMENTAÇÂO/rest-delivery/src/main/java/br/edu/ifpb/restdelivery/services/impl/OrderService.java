package br.edu.ifpb.restdelivery.services.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import br.edu.ifpb.restdelivery.dao.OrderDAO;
import br.edu.ifpb.restdelivery.entities.Order;
import br.edu.ifpb.restdelivery.entities.ResultMap;
import br.edu.ifpb.restdelivery.exceptions.RestDeliveryPersistenceException;

/**
 * Classe que fornece serviços da entidade Order
 * 
 * @author rafaelfeitosa - <a href="https://github.com/JoseRafael97 ></a>
 *
 */
public class OrderService extends ImplGenericService<Order, Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 	 * * Construtor injetado com CDI para utilizar o DAO generico, nele passamos
	 * o tipo do dao genérico de Customer.
	 * @param orderDAO
	 */
	@Inject
	public OrderService(OrderDAO orderDAO) {
		this.dao = orderDAO;
	}
	
	
	/**
	 * Método que  retorna o valor de receita com pedidos e data que isso ocorreu na forma de um mapa.
	 * @param days
	 * @return
	 * @throws RestDeliveryPersistenceException
	 */
	public Map<Date, BigDecimal> allValuesToDate(Integer days) throws RestDeliveryPersistenceException{
		return ((OrderDAO) this.dao).allValuesToDate(days);
	}
	
	/**
	 * Método que conta o número de pedidos cadastrados.
	 * @return
	 */
	public Long countAll(){
		return ((OrderDAO)this.dao).countAll();
	}
	
	/**
	 * Método que conta a quantidade de avaliaçãoes já realizadas, considerando todos os pedidos
	 * @return
	 */
	public Long countRatingAll(){
		return ((OrderDAO)this.dao).countRatingAll();
	}
	
	/**
	 * Método que consulta os 5 ultimos pedidos avaliados.
	 * @return
	 */
	public List<Order> findOrderToRating(){
		return ((OrderDAO)this.dao).findOrderToRating();
	}
	
	/**
	 * Busca itens mais comprados.
	 * @param numero
	 * @param numberDays
	 * @return
	 */
	public Map<Date, ResultMap>  findSoBuy(Integer numberDays) {
		return ((OrderDAO)this.dao).findSoBuy(numberDays);
	}
	
	/**
	 * Busca o nome 
	 * @param number
	 * @return
	 */
	public Map<String, Long> findOrderToAmountBuy(Long number){
		return ((OrderDAO)this.dao).findOrderToAmountBuy(number);
	}
	
	/**
	 * Buscar por Media da Loja de acordo com data.
	 * @param numberDays
	 * @return
	 */
	public Map<Date, Double> findAverageBuy(Integer numberDays){
		return ((OrderDAO)this.dao).findAverageBuy(numberDays);

	}

}
