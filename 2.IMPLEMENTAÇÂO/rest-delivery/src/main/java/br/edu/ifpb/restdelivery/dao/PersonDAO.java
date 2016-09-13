package br.edu.ifpb.restdelivery.dao;

import br.edu.ifpb.restdelivery.entities.Person;

/**
 * Interface DAO para Pessoa.
 * 
 * @author rafaelfeitosa - <a href="https://github.com/JoseRafael97 ></a>
 *
 */
public interface PersonDAO extends GenericDAO<Person, Long>{
	
	
	/**
	 * Busca no banco uma pessoa com o CPF passado.
	 * @param cpf
	 * @return Person com o CPF passado.
	 */
	public Person findByCpf(String cpf);

}
