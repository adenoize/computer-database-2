/**
 * 
 */
package com.excilys.cdb.main.java.service;

/**
 * @author Aurelien Denoize
 *
 */
public class RemoveException extends Exception {

	
	private static final long serialVersionUID = 3667010634674359747L;


	public RemoveException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public RemoveException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public RemoveException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public RemoveException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public RemoveException(Throwable cause) {
		super(cause);
	}

	
}
