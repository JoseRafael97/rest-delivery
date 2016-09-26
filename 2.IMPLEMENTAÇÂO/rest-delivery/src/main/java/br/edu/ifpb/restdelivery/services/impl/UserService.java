package br.edu.ifpb.restdelivery.services.impl;

import javax.inject.Inject;

import br.edu.ifpb.restdelivery.dao.UserDAO;
import br.edu.ifpb.restdelivery.entities.User;
import br.edu.ifpb.restdelivery.exceptions.RestDeliveryException;
import br.edu.ifpb.restdelivery.util.jpa.Transactional;
import br.edu.ifpb.restdelivery.validators.ValidatorPerson;

/**
 * Classe que fornece serviços da entidade User.
 *  
 * @author rafaelfeitosa - <a href="https://github.com/JoseRafael97 ></a>
 *
 */
public class UserService extends ImplGenericService<User, Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	@Transactional
	public void update(User user) throws RestDeliveryException {
		ValidatorPerson.validateUniqueLoginUser(user.getPerson(),this);
		ValidatorPerson.validateUniqueEmailUser(user.getPerson(), this);
		super.update(user);
	}
	
	/**
	 * 	 * * Construtor injetado com CDI para utilizar o DAO generico, nele passamos
	 * o tipo do dao genérico de User.
	 * @param userDAO
	 */
	@Inject
	public UserService(UserDAO userDAO) {
		this.dao = userDAO;
	}
	
	/**
	 * Método que busca pelo login o usuário
	 * @param name
	 * @return
	 */
	public User findByName(String name){
		return ((UserDAO)this.dao).findByName(name);
	}
	
	/**
	 * Método que busca um usuário pelo seu e-mail.
	 * @param email
	 * @return
	 */
	public User findByEmail(String email){
		return ((UserDAO)this.dao).findByEmail(email);
	}

}
