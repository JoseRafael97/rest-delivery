package br.edu.ifpb.restdelivery.dao.impl;

import java.io.Serializable;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.edu.ifpb.restdelivery.dao.UserDAO;
import br.edu.ifpb.restdelivery.entities.User;

/**
 * Implementação do DAO de Usuário e subclasse  da implementação de GenericDAO.
 * 
 * @author rafaelfeitosa - <a href="https://github.com/JoseRafael97 ></a>
 *
 */
public class ImplUserDAO extends ImplGenericDAO<User, Long> implements UserDAO,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Método que busca o usuário com mesmo login passado no parâmetro.
	 */
	public User findByName(String login) {
		try {
			TypedQuery<User> query = em.createNamedQuery("user.findByName", User.class);
			query.setParameter("login", login);
			return query.getSingleResult();

		} catch (NoResultException e) {
			return null;
		}

	}

	/**
	 * Método que busca o usuário com o mesmo e-mail passado no parâmetro.
	 */
	public User findByEmail(String email) {
		try {
			TypedQuery<User> query = em.createNamedQuery("user.findByEmail", User.class);
			query.setParameter("email", email);
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
}
