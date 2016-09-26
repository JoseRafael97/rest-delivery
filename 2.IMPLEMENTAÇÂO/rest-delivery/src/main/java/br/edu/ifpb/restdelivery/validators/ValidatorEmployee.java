package br.edu.ifpb.restdelivery.validators;

import br.edu.ifpb.restdelivery.entities.Employee;
import br.edu.ifpb.restdelivery.exceptions.ConstraintViolateException;
import br.edu.ifpb.restdelivery.services.impl.EmployeeService;
import br.edu.ifpb.restdelivery.services.impl.PersonService;
import br.edu.ifpb.restdelivery.services.impl.UserService;

/**
 * Classe que contém alguns validadores para Employee
 * 
 * @author rafaelfeitosa - <a href="https://github.com/JoseRafael97 ></a>
 *
 */
public class ValidatorEmployee extends ValidatorPerson {

	
	/**
	 * Método que verifica se o cpf do empregado é unico
	 * @param employee
	 * @param employeeService
	 * @throws ConstraintViolateException
	 */
	public static void validateUniqueCtps(Employee employee, EmployeeService employeeService) throws ConstraintViolateException {
		Employee emp = employeeService.findByCtps(employee.getCtps());

		if (emp != null && employee.getId() != null && emp.getId().equals(employee.getId())) {
			return;

		}else if (emp != null && emp.getId() != employee.getId()) {
			throw new ConstraintViolateException("CTPS Já cadastrada");
		}
	}
	
	

	
	/**
	 * Método que verifica se O RG do empregado é único.
	 * @param employee
	 * @param employeeService
	 * @throws ConstraintViolateException
	 */
	public static void validateUniqueRg(Employee employee,EmployeeService employeeService) throws ConstraintViolateException {
		Employee emp = employeeService.findByRg(employee.getRg());

		if (emp != null && employee.getId() != null && emp.getId().equals(employee.getId())) {
			return;

		} else if (emp != null && emp.getId() != employee.getId()) {
			throw new ConstraintViolateException("RG já cadastrado");
		}
	}
	
	
	/**
	 * Método que verifica se O telefone do empregado é único.
	 * @param employee
	 * @param employeeService
	 * @throws ConstraintViolateException
	 */
	public static void validateUniqueTellPhone(Employee employee, EmployeeService employeeService) throws ConstraintViolateException {
		Employee emp = employeeService.findByTellPhone(employee.getTellphone());
		
		if (emp != null && employee.getId() != null && emp.getId().equals(employee.getId())) {
			return;

		} else if (emp != null && emp.getId() != employee.getId()) {
			throw new ConstraintViolateException("Celular já cadastrado");
		}
	}

	/**
	 * Método agregador de validações, que verifica unicidade, cpf, login, email, ctps,rg, telefone.
	 * @param personService
	 * @param userService
	 * @param employeeService
	 * @param employee
	 * @throws ConstraintViolateException
	 */
	public static void validateEmployee(PersonService personService, UserService userService, EmployeeService employeeService,
			Employee employee) throws ConstraintViolateException {
		validateUniqueCpf(employee, personService);
		validateUniqueLoginUser(employee, userService);
		validateUniqueEmailUser(employee, userService);
		validateUniqueCtps(employee, employeeService);
		validateUniqueRg(employee, employeeService);
		validateUniqueTellPhone(employee, employeeService);
	}
}
