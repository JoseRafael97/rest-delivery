package br.edu.ifpb.restdelivery.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaQuery;

import br.edu.ifpb.restdelivery.entities.Product;
import br.edu.ifpb.restdelivery.exceptions.RestDeliveryPersistenceException;
import br.edu.ifpb.restdelivery.util.filters.FilterProducts;

/**
 *Interface DAO para Product.
 * 
 * @author rafaelfeitosa - <a href="https://github.com/JoseRafael97 ></a>
 *

 */
public interface ProductDAO extends GenericDAO<Product, Long>{
	
	

}
