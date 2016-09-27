package br.edu.ifpb.restdelivery.controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;


/**
 * Classe abstrata que fornece alguns métodos que podem ser reutilizados.
 * 
 * @author rafaelfeitosa - <a href="https://github.com/JoseRafael97 ></a>
 *
 */
public abstract class AbstractBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7887186144461468149L;

	/**
	 *Método que exibi mensagem de erro na página para jsf, informando os detalhes passado no parâmetros. 
	 * 
	 * @param detalhe
	 */
	protected void reportErroMensage(String detalhe) {
		reportarMensagem(true, detalhe);

	}

	/**
	 * Método que exibi mensagem de sucesso para jsf, informando os detalhes passado no parâmetro
	 * 
	 * @param detail
	 */
	protected void reportSuccessMensage(String detail) {
		reportarMensagem(false, detail);
	}

	/**
	 *Método que cria uma mensagem de erro ou sucesso dependendo o parâmentro informando se é erro ou não.
	 * @param isErro
	 * @param detalhe
	 */
	protected void reportarMensagem(boolean isErro, String detalhe) {
		String tipo = "Sucesso!";
		Severity severity = FacesMessage.SEVERITY_INFO;
		
		if (isErro) {
			tipo = "Erro!";
			severity = FacesMessage.SEVERITY_ERROR;
			FacesContext.getCurrentInstance().validationFailed();
		}
		
	

		FacesMessage msg = new FacesMessage(severity, tipo, detalhe);

		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.setKeepMessages(true);
		flash.setRedirect(true);
		FacesContext.getCurrentInstance().addMessage(null, msg);

	}

}