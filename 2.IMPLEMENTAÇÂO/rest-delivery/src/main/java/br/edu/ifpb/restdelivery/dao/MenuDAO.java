package br.edu.ifpb.restdelivery.dao;



import br.edu.ifpb.restdelivery.entities.Menu;

public interface MenuDAO extends GenericDAO<Menu, Long> {

	/**
	 * Busca o menu ativo no momento para compras de itens. Só pode estar ativo
	 * um menu por vez.
	 *
	 * @return Menu em atividade no momento
	 */
	public Menu findByMenuActive();

	/**
	 * Busca o id de um produto que pertenca ao cardápio pelo seu id.
	 * 
	 * @return Produto contido no menu com mesmo id passado.
	 */
	public Long findByProductMenu(Long id);


	/**
	 * busca todos o número de menus registrados no sistema
	 * 
	 * @return long com o numero exato de menus.
	 */
	public Long countAll();
	

}
