package br.edu.ifpb.restdelivery.dao.impl;

import java.io.Serializable;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.edu.ifpb.restdelivery.dao.MenuDAO;
import br.edu.ifpb.restdelivery.entities.Menu;

/**
 * Implementação do DAO de cardápio e subClasse da implementação de GenericDAO.
 * 
 * @author rafaelfeitosa - <a href="https://github.com/JoseRafael97 ></a>
 *
 */
public class ImplMenuDAO extends ImplGenericDAO<Menu, Long> implements MenuDAO, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Método que busca por um menu ativo é o retorna se existir.
	 */
	public Menu findByMenuActive() {

		try {
			TypedQuery<Menu> query = em.createNamedQuery("menu.findSelectMenu", Menu.class);
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}

	}

	/**
	 * Método que busca por um produto existente no menu.
	 */
	@Override
	public Long findByProductMenu(Long id) {

		try {
			TypedQuery<Long> query = em.createNamedQuery("menu.findByProductMenu", Long.class);
			query.setParameter("id", id);
			return query.getSingleResult();

		} catch (NoResultException e) {
			return null;
		}

	}


	/**
	 * Método que conta o número de todos os cardápios cadastrados.
	 */
	@Override
	public Long countAll() {
		try {
			TypedQuery<Long> query = em.createNamedQuery("menu.countAll", Long.class);
			return query.getSingleResult();

		} catch (NoResultException e) {
			return null;
		}

	}
	
	

}
