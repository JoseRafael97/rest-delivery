package br.edu.ifpb.restdelivery.controller;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.restdelivery.entities.Employee;
import br.edu.ifpb.restdelivery.exceptions.RestDeliveryException;
import br.edu.ifpb.restdelivery.services.impl.EmployeeService;

/**
 * Classe controller para lista de Empregados.
 * @author rafaelfeitosa - <a href="https://github.com/JoseRafael97 ></a>
 *
 */
@Named
@RequestScoped
public class ListEmployee extends AbstractBean{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Employee> employees;
	
	private List<Employee> filteredEmployees;
	
	@Inject
	private EmployeeService employeeService;

	/**
	 * Retorna lista de empregados.
	 * @return
	 * @throws RestDeliveryException
	 */
	public List<Employee> getEmployees() throws RestDeliveryException {
		if(employees == null){
			employees = employeeService.listAll();
		}
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
	
	
	
	public void remove(Employee employee){
		try {
			employeeService.remove(employee);
			employees.remove(employee);
			reportSuccessMensage("Funcionário removido!");
		} catch (RestDeliveryException e) {
			reportErroMensage(e.getMessage());
			e.printStackTrace();
		}
	}

	public List<Employee> getFilteredEmployees() {
		return filteredEmployees;
	}

	public void setFilteredEmployees(List<Employee> filteredEmployees) {
		this.filteredEmployees = filteredEmployees;
	}

	
	

}
