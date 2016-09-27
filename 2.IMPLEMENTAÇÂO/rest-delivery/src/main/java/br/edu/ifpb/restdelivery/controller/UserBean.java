package br.edu.ifpb.restdelivery.controller;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.restdelivery.entities.User;
import br.edu.ifpb.restdelivery.exceptions.RestDeliveryException;
import br.edu.ifpb.restdelivery.services.impl.UserService;
import br.edu.ifpb.restdelivery.util.tools.Tools;
/**
 * Classe controller para User. 
 * @author rafaelfeitosa - <a href="https://github.com/JoseRafael97 ></a>
 *
 */
@Named
@ViewScoped
public class UserBean extends AbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private User user;
	private String actuallyPassword;
	private String newPassword;
	
	@Inject
	private UserService userService;

	/**
	 * Atualiza conta de usuário.
	 * @throws RestDeliveryException
	 */
	public void updateUser() throws RestDeliveryException {

		if (actuallyPassword != null) {
			actuallyPassword = Tools.criptografarSenha(actuallyPassword);
			
			if (!actuallyPassword.equals(user.getPassword())) {
				reportErroMensage("Senha atual incorreta.");
				return;
			}
		}
		user.setPassword(Tools.criptografarSenha(newPassword));
		userService.update(user);
		reportSuccessMensage("Conta atualizada com sucesso.");
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getActuallyPassword() {
		return actuallyPassword;
	}

	public void setActuallyPassword(String actuallyPassword) {
		this.actuallyPassword = actuallyPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
	
}
