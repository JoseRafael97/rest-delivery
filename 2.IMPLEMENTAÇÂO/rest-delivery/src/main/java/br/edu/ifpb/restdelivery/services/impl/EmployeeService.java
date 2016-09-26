package br.edu.ifpb.restdelivery.services.impl;

import javax.inject.Inject;

import br.edu.ifpb.restdelivery.dao.EmployeeDAO;
import br.edu.ifpb.restdelivery.entities.Employee;

/**
 * Classe que provê servidos da entidade Employee
 * 
 * @author rafaelfeitosa - <a href="https://github.com/JoseRafael97 ></a>
 *
 */
public class EmployeeService extends ImplGenericService<Employee, Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 	 * * Construtor injetado com CDI para utilizar o DAO generico, nele passamos
	 * o tipo do dao genérico de Employee.
	 * @param employeeDAO
	 */
	@Inject
	public EmployeeService(EmployeeDAO employeeDAO) {
		this.dao = employeeDAO;
	}


	/**
	 * Método que busca o Empregado pelo seu telefone.
	 * @param phone
	 * @return
	 */
	public Employee findByTellPhone (String phone){
		return ((EmployeeDAO)dao).findByTellPhone(phone);
	}
	
	/**
	 * Método que busca o empregado por seu RG.
	 * @param rg
	 * @return
	 */
	public Employee findByRg(String rg){
		return ((EmployeeDAO)dao).findByRg(rg);
	}
	
	/**
	 * Método que busca o empregado pelo no número da ctps
	 * @param ctps
	 * @return
	 */
	public Employee findByCtps(String ctps){
		return ((EmployeeDAO)dao).findByCtps(ctps);
	}
}
