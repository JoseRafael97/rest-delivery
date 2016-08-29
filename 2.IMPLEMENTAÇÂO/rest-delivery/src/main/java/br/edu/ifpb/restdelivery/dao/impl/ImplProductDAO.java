package br.edu.ifpb.restdelivery.dao.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


import br.edu.ifpb.restdelivery.dao.ProductDAO;
import br.edu.ifpb.restdelivery.entities.Product;
import br.edu.ifpb.restdelivery.enumerations.CategoryType;
import br.edu.ifpb.restdelivery.exceptions.RestDeliveryPersistenceException;
import br.edu.ifpb.restdelivery.util.filters.FilterProducts;

public class ImplProductDAO extends ImplGenericDAO<Product, Long> implements ProductDAO, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	

	
	

}