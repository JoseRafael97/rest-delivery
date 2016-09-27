package br.edu.ifpb.restdelivery.dao.impl;

import java.io.Serializable;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.edu.ifpb.restdelivery.dao.PersonDAO;
import br.edu.ifpb.restdelivery.entities.Person;

/**
 * Implementação do DAO de Pessoa e subclasse da implementação do GenericDAO.
 * 
 * @author rafaelfeitosa - <a href="https://github.com/JoseRafael97 ></a>
 *
 */
public class ImplPersonDAO extends ImplGenericDAO<Person, Long> implements PersonDAO,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Método que busca um pessoa com o mesmo cpf passado como parâmetro
	 */
	public Person findByCpf(String cpf) {
		try {
			
			TypedQuery<Person> query = em.createNamedQuery("person.findByCpf", Person.class);
			query.setParameter("cpf", cpf);
			return query.getSingleResult();

		} catch (NoResultException e) {
			return null;
		}
	}

}
