package br.edu.ifpb.restdelivery.exceptions;

public class LoginOrEmailInvalidoException extends RestDeliveryException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LoginOrEmailInvalidoException(String msg) {
		super(msg);
	}

}
