package br.edu.ifpb.restdelivery.util.jsf;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;

/**
 * Classe resposável por criar JsfExceptionHandler
 * 
 * @author rafaelfeitosa - <a href="https://github.com/JoseRafael97 ></a>
 *
 */
public class JsfExceptionHandlerFactory extends ExceptionHandlerFactory{

	
	private ExceptionHandlerFactory handlerFactory;
	
	public JsfExceptionHandlerFactory(ExceptionHandlerFactory exceptionHandlerFactory) {
		this.handlerFactory = exceptionHandlerFactory;
	}
	
	@Override
	public ExceptionHandler getExceptionHandler() {
		return new JsfExceptionHandler(handlerFactory.getExceptionHandler());
	}

}
