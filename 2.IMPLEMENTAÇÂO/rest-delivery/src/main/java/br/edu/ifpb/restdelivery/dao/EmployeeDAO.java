package br.edu.ifpb.restdelivery.dao;

import br.edu.ifpb.restdelivery.entities.Employee;

/**
 * Interface DAO para Empregado
 * 
 * @author rafaelfeitosa - <a href="https://github.com/JoseRafael97 ></a>
 *
 */
 
public interface EmployeeDAO  extends GenericDAO<Employee, Long>{
	
	
	
	/**
	 * Busca no banco um empregado de acordo com a sua ctps.
	 * @param ctps - número da carteira de trabalho do empregado.
	 * @return Empregado com o ctps passada.
	 */
	public Employee findByCtps(String ctps) ;
	
	
	/**
	 * Busca no banco um empregado de acordo com o rg passado
	 * @param rg - Registro geral do empregado.
	 * @return - Empregado com RG passado.
	 */
	public Employee findByRg(String rg);
	
	
	/**
	 * Busca no banco o empregado com o telefone passado
	 * @param tellphone
	 * @return - Empregado com telefone passado.
	 */
	public Employee findByTellPhone(String tellphone) ;

}
