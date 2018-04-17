/**
 * 
 */
package com.excilys.cdb.main.java.service;

/**
 * @author Aurelien Denoize
 *
 */
public class UpdateException extends Exception {

	
	private static final long serialVersionUID = 1720385584631139347L;


	public UpdateException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public UpdateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public UpdateException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public UpdateException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public UpdateException(Throwable cause) {
		super(cause);
	}
	
	

}
