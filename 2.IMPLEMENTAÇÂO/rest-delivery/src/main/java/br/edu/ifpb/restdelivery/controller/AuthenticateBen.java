package br.edu.ifpb.restdelivery.controller;

import java.io.IOException;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import javax.servlet.http.HttpSession;

import br.edu.ifpb.restdelivery.entities.Customer;
import br.edu.ifpb.restdelivery.entities.Employee;
import br.edu.ifpb.restdelivery.entities.User;
import br.edu.ifpb.restdelivery.enumerations.Grupo;
import br.edu.ifpb.restdelivery.exceptions.RestDeliveryException;
import br.edu.ifpb.restdelivery.services.impl.UserService;
import br.edu.ifpb.restdelivery.util.tools.Tools;

/**
 * Controller para lidae Com login do usuário
 * 
 * @author rafaelfeitosa - <a href="https://github.com/JoseRafael97 ></a>
 *
 */
@Named
@SessionScoped
public class AuthenticateBen extends AbstractBean {

	/**
	 * 
	 */

	private User user;

	@Inject
	private UserService userService;

	private static final long serialVersionUID = 1L;

	/**
	 * Método que recupera Usuário logado.
	 * 
	 * @return
	 * @throws RestDeliveryException 
	 */
	public User getUser() throws RestDeliveryException {
		if (user == null && recoversUserName() != null) {
			return userService.findByName(recoversUserName());
		}
		user = new User();
		return user;
	}

	/**
	 * Recupera o login do usuário logado 
	 * @return
	 * @throws RestDeliveryException 
	 */
	public String recoversUserName() throws RestDeliveryException {
		
		String name = Tools.recoversUserName();
		if (name == null) {
			throw new RestDeliveryException("Não existe nenhum usuário logado");
		}
		return name;
	}

	/**
	 * Método que faz logout do usuário inválidando sua sessão e retornando a página inicial da aplicação
	 * @return
	 */
	public String logout() {

		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.invalidate();

		return "/pages/index?faces-redirect=true";

	}

	/**
	 * Método que checar qual o tipo do usuário logado.
	 * @throws RestDeliveryException 
	 */
	public void checkUserRole() throws RestDeliveryException {
		try {
			if (getUser() != null) {
				
				String contextPath = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
				if (getUser().getGrupo().equals(Grupo.CLIENTE)) {
					FacesContext.getCurrentInstance().getExternalContext().redirect(contextPath + "/pages/customer/main_page.xhtml");

				} else if (getUser().getGrupo().equals(Grupo.ENTREGADOR)) {

					FacesContext.getCurrentInstance().getExternalContext().redirect(contextPath + "/pages/employee/deliveryman/order.xhtml");

				} else {
					FacesContext.getCurrentInstance().getExternalContext()
							.redirect(contextPath + "/pages/employee/main_page_emp.xhtml");

				}
			} else {
				FacesContext.getCurrentInstance().getExternalContext().redirect("error.xhtml");

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método que checar qual tipo de empregado está logado.
	 * @return
	 * @throws RestDeliveryException 
	 */
	public int checkUserRoleEmployee() throws RestDeliveryException {

		if (getUser() != null) {
			if (getUser().getGrupo().getValor().equals(Grupo.ENTREGADOR.getValor())) {
				return 1;

			} else if (getUser().getGrupo().getValor().equals(Grupo.OPERADOR.getValor())) {
				return 2;
			}

			return 3;
		}
		return 0;
	}
	
	/**
	 * Método que checa se o usuário é um entregador.
	 * @return true se usuário for entregador
	 * @throws RestDeliveryException 
	 */
	public boolean iCheckUserDeliveryMan() throws RestDeliveryException{
		if(getUser() != null && getUser().getGrupo().equals(Grupo.ENTREGADOR)){
			return true;
		}
		
		return false;
	}
	
	/**
	 * Método que retorna o cliente logado.
	 * @return
	 * @throws RestDeliveryException 
	 */
	public Customer getCustomer() throws RestDeliveryException{
		if (getUser().getGrupo().equals(Grupo.CLIENTE)) {
			return (Customer) getUser().getPerson();
		}
		
		return null;
	}
	
	/**
	 * Método que retorna o empregado logado
	 * @return
	 * @throws RestDeliveryException 
	 */
	public Employee getEmployee() throws RestDeliveryException{
		if (!getUser().getGrupo().equals(Grupo.CLIENTE)) {
			return (Employee) getUser().getPerson();
		}
		
		return null;
	}
	
	/**
	 * Recupera o nome do usuario em caixa alta.
	 * @return
	 * @throws RestDeliveryException 
	 */
	public String recoversUserCustomerName() throws RestDeliveryException {
		return recoversUserName().toUpperCase();
	}
	

}
