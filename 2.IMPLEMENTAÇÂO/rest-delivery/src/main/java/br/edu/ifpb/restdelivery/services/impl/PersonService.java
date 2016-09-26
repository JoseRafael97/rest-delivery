package br.edu.ifpb.restdelivery.services.impl;

import javax.inject.Inject;

import br.edu.ifpb.restdelivery.dao.PersonDAO;
import br.edu.ifpb.restdelivery.entities.Person;

/**
 * Classe que fornece serviços da classe Person
 * 
 * @author rafaelfeitosa - <a href="https://github.com/JoseRafael97 ></a>
 *
 */
public class PersonService extends ImplGenericService<Person, Long> {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;

	/**
	 * * * Construtor injetado com CDI para utilizar o DAO generico, nele
	 * passamos o tipo do dao genérico de Person.
	 * 
	 * @param personDAO
	 */
	@Inject
	public PersonService(PersonDAO personDAO) {
		this.dao = personDAO;
	}

	/**
	 * Método que busca uma pessoa pelo seu CPF 
	 * @param cpf
	 * @return - pessoa com mesmo cpf informado.
	 */
	public Person findByCpf(String cpf) {
		return ((PersonDAO) dao).findByCpf(cpf);
	}

}
