package br.edu.ifpb.restdelivery.controller;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import br.edu.ifpb.restdelivery.entities.Address;
import br.edu.ifpb.restdelivery.entities.Employee;
import br.edu.ifpb.restdelivery.entities.User;
import br.edu.ifpb.restdelivery.enumerations.Grupo;
import br.edu.ifpb.restdelivery.exceptions.RestDeliveryException;
import br.edu.ifpb.restdelivery.services.impl.EmployeeService;
import br.edu.ifpb.restdelivery.services.impl.PersonService;
import br.edu.ifpb.restdelivery.services.impl.UserService;
import br.edu.ifpb.restdelivery.util.tools.Tools;
import br.edu.ifpb.restdelivery.validators.ValidatorEmployee;

/**
 * Classe controlle para Employee.
 * @author rafaelfeitosa - <a href="https://github.com/JoseRafael97 ></a>
 *
 */
@Named
@ViewScoped
public class EmployeeBean extends AbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private EmployeeService employeeService;

	@Inject
	private PersonService personService;

	@Inject
	private UserService userService;

	private Employee employee;
	private Grupo grupo;
	private Grupo[] groups;

	private String senhaAtual;

	/**
	 * Método resposável por inicializar atributos para evitar erros de com objetos nulos.
	 */
	@PostConstruct
	public void init() {

		if (employee == null) {
			employee = new Employee();
			User user = new User();
			employee.setUser(user);
			employee.setAddress(new Address());
		}

		if (groups == null) {
			Grupo[] g = { Grupo.ADMIN, Grupo.OPERADOR, Grupo.ENTREGADOR };
			groups = g;
		}

	}

	/**
	 * Método reponsável por salvar um empregado 
	 * @return
	 * @throws RestDeliveryException
	 */
	public String save() throws RestDeliveryException {

		employee.getUser().setPerson(employee);

		if (employee.getUser().getPassword() == null) {
			employee.getUser().setPassword(employee.getCpf());
		}

		employee.getUser().setPassword(Tools.criptografarSenha(employee.getUser().getPassword()));
		ValidatorEmployee.validateEmployee(personService, userService, employeeService, employee);

		employee.getUser().setGrupo(grupo);

		if (employee.getId() == null) {
			employeeService.save(employee);
			employee = new Employee();
			reportSuccessMensage("Novo funcionário cadastrado!");

		} else {

			employeeService.update(employee);
			reportSuccessMensage("funcionário alterado");
			employee = new Employee();
			return "employee?faces-redirect=true";

		}
		return null;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Grupo[] getGroups() {
		return groups;
	}

	public void setGroups(Grupo[] groups) {
		this.groups = groups;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public String getSenhaAtual() {
		return senhaAtual;
	}

	public void setSenhaAtual(String senhaAtual) {
		this.senhaAtual = senhaAtual;
	}

}
