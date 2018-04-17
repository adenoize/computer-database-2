/**
 * 
 */
package com.excilys.cdb.main.java.service;

/**
 * @author Aurelien Denoize
 *
 */
public class SaveException extends Exception {


	private static final long serialVersionUID = 1230092777707968357L;
	
	public SaveException() {}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public SaveException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public SaveException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public SaveException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public SaveException(Throwable cause) {
		super(cause);
	}
	
	

}
