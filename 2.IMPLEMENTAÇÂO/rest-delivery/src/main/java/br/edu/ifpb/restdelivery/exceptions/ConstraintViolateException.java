package br.edu.ifpb.restdelivery.exceptions;

public class ConstraintViolateException extends RestDeliveryException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ConstraintViolateException(String msg) {
		super(msg);
	}

}
