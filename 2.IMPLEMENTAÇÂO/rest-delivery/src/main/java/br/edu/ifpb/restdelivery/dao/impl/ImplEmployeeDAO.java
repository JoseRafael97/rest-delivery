package br.edu.ifpb.restdelivery.dao.impl;

import java.io.Serializable;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.edu.ifpb.restdelivery.dao.EmployeeDAO;
import br.edu.ifpb.restdelivery.entities.Employee;

/**
 * Implementação do DAO de Empregadoe e Subclasse do supertipo da implementação do GenericDAO 
 * 
 * @author rafaelfeitosa - <a href="https://github.com/JoseRafael97 ></a>
 *
 */
public class ImplEmployeeDAO extends ImplGenericDAO<Employee, Long> implements EmployeeDAO,Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Método que busca Empregado pela CTPS que única.
	 */
	public Employee findByCtps(String ctps) {
		try {
			TypedQuery<Employee> query = em.createNamedQuery("employee.findByCtps", Employee.class);
			query.setParameter("ctps", ctps);
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	/**
	 * Método que busca Empregado pelo RG que único.
	 */
	public Employee findByRg(String rg) {
		try {

			TypedQuery<Employee> query = em.createNamedQuery("employee.findByRg", Employee.class);
			query.setParameter("rg", rg);
			return query.getSingleResult();

		} catch (NoResultException e) {
			return null;
		}
	}

	/**
	 * Método que busca empregado pelo seu telefone.
	 */
	public Employee findByTellPhone(String tellphone) {
		try {
			TypedQuery<Employee> query = em.createNamedQuery("employee.findByTellPhone", Employee.class);
			query.setParameter("tellphone", tellphone);
			return query.getSingleResult();

		} catch (NoResultException e) {
			return null;
		}
	}

	
}
