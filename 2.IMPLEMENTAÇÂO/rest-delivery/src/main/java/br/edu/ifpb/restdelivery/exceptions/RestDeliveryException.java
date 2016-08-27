package br.edu.ifpb.restdelivery.exceptions;

public class RestDeliveryException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7669751088704144947L;

	public RestDeliveryException(String mensagem) {
		super(mensagem);
	}

	public RestDeliveryException(String mensagem, Throwable throwable) {
		super(mensagem, throwable);
	}

}
