package br.edu.ifpb.restdelivery.util.jsf;

import java.io.IOException;
import java.util.Iterator;

import javax.faces.FacesException;
import javax.faces.application.ViewExpiredException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;
import javax.validation.ConstraintViolationException;

import br.edu.ifpb.restdelivery.exceptions.RestDeliveryException;

/**
 * Classe responsável por controlar erros e tratar erros durante a execução do sistema.
 * 
 * @author rafaelfeitosa - <a href="https://github.com/JoseRafael97 ></a>
 *
 */
public class JsfExceptionHandler extends ExceptionHandlerWrapper {

	private ExceptionHandler exceptionHandeler;

	public JsfExceptionHandler(ExceptionHandler exceptionHandler) {
		this.exceptionHandeler = exceptionHandler;
	}

	@Override
	public ExceptionHandler getWrapped() {
		return this.exceptionHandeler;
	}

	/**
	 * Método resposável por verificar e tomar medidas na ocorrência de erros como por exemplo encaminhar para uma página de erro e etc.
	 */
	@Override
	public void handle() throws FacesException {
		Iterator<ExceptionQueuedEvent> events = getUnhandledExceptionQueuedEvents().iterator();

		while (events.hasNext()) {
			ExceptionQueuedEvent event = events.next();
			ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event.getSource();

			Throwable exception = context.getException();
			RestDeliveryException deliveryRun = getRestDeliveryException(exception);
			ConstraintViolationException constraintViolationException = getConstraintViolationException(exception);
			boolean hadler = false;

			try {

				if (exception instanceof ViewExpiredException) {
					redirect("/");
					hadler = true;
				} else if (deliveryRun != null) {
					hadler = true;
					FacesUtil.addErrorMessage(deliveryRun.getMessage());
				
				} else if (constraintViolationException != null) {
					hadler = true;
					FacesUtil.addErrorMessage("Verifique que o item não estar em uso");

				} else {
					hadler = true;
					redirect("/error.xhtml");
				}
			} finally {
				if (hadler) {
					events.remove();
				}
			}
		}

		getWrapped().handle();
	}

	private RestDeliveryException getRestDeliveryException(Throwable exception) {
		if (exception instanceof RestDeliveryException) {
			return (RestDeliveryException) exception;
		} else if (exception.getCause() != null) {
			return getRestDeliveryException(exception.getCause());
		}
		return null;
	}

	private ConstraintViolationException getConstraintViolationException(Throwable exception) {
		if (exception instanceof ConstraintViolationException) {
			return (ConstraintViolationException) exception;
		} else if (exception.getCause() != null) {
			return getConstraintViolationException(exception.getCause());
		}
		return null;
	}

	private void redirect(String page) {

		try {

			FacesContext facesContext = FacesContext.getCurrentInstance();
			ExternalContext externalContext = facesContext.getExternalContext();
			String contextPath = externalContext.getRequestContextPath();

			externalContext.redirect(contextPath + page);
			facesContext.responseComplete();

		} catch (IOException e) {
			throw new FacesException(e);
		}
	}

}
