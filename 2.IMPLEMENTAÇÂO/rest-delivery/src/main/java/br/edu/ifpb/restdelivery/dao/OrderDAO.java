package br.edu.ifpb.restdelivery.dao;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import br.edu.ifpb.restdelivery.entities.Order;
import br.edu.ifpb.restdelivery.exceptions.RestDeliveryPersistenceException;


/**
 * Interface DAO para Pedidos, que implementa GENERICDAO
 * 
 * @author rafaelfeitosa - <a href="https://github.com/JoseRafael97 ></a>
 *
 */
public interface OrderDAO extends GenericDAO<Order, Long> {

	/**
	 * Método que filtra os valores de arrecadação por pedidos ordenados pela data.
	 * @param numberDays - número dias que se deve filtrar
	 * @return - MAPA com datas e o valor de arrecação naquela data.
	 * @throws RestDeliveryPersistenceException
	 */
	public Map<Date, BigDecimal> allValuesToDate(Integer numberDays) throws RestDeliveryPersistenceException;

	/**
	 * Método que cria mapa vazio colocando valores zerados para evitar exibir com valores null
	 * @param numberDays - número de dias que deseja que mapa de suporte
	 * @param initialDate - data inicial para ser adicionado no mapa.
	 * @return
	 */
	public Map<Date, BigDecimal> createMap(Integer numberDays, Calendar initialDate);
	
	/**
	 * Método que conta o número de pedidos cadastrados no banco de dados.
	 * @return
	 */
	public Long countAll();
	
	/**
	 * Método que conta o número de avalições feitas, considerandos todos os pedidos já realizados. 
	 * @return
	 */
	public Long countRatingAll();
	
	
	/**
	 * Método que buscar os pedidos com avalição registrada agrupados por data.
	 * @return
	 */
	public List<Order> findOrderToRating();

}
