package br.edu.ifpb.restdelivery.util.jsf;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * Classe que provê alguns rescursos para exibir mensagem no front-end com jsf
 * 
 * @author rafaelfeitosa - <a href="https://github.com/JoseRafael97 ></a>
 *
 */
public class FacesUtil {

	/**
	 * Método que exibir mensagem de sucesso com informações passadas no parâmento
	 * @param message
	 */
	public static void addSuccessMessage(String message) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, message, message));
	}

	/**
	 * Método que exibir uma mensagem de erro com detalhes passados no parâmetro.
	 * @param message
	 */
	public static void addErrorMessage(String message) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message));
	}

}