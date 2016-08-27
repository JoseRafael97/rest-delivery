package br.edu.ifpb.restdelivery.exceptions;



public class RestDeliveryPersistenceException extends RestDeliveryException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1780243892137781599L;

	public RestDeliveryPersistenceException(String mensagem) {
		super(mensagem);
	}

	public RestDeliveryPersistenceException(String mensagem, Throwable throwable) {
		super(mensagem, throwable);
	}

}
