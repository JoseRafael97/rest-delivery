package br.edu.ifpb.restdelivery.dao;

import br.edu.ifpb.restdelivery.entities.User;

/**
 * Interface DAO para Usuário.
 * 
 * @author rafaelfeitosa - <a href="https://github.com/JoseRafael97 ></a>
 *
 */
public interface UserDAO extends GenericDAO<User, Long>{
	
	/**
	 * Método que busca um usuário de acordo com o Login passado como parâmestro.
	 * @param login 
	 * @return - Usuário com o login passado.
	 */
	public User findByName(String login);
	
	/**
	 * Método que busca um usuário com o e-mail passado como parâmetro.
	 * @param email
	 * @return - Usuário com o mesmo e-mail passado.
	 */
	public User findByEmail(String email);

}
