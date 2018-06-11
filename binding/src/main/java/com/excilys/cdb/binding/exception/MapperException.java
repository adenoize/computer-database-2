package com.excilys.cdb.binding.exception;

/**
 * @author Aurelien Denoize Excilys 2018
 */
public class MapperException extends Exception {

    private static final long serialVersionUID = 4432318702795512432L;

    /**
     */
    public MapperException() {
        super();
    }

    /**
     * Constructor with message.
     * @param message The message of the exception
     */
    public MapperException(String message) {
        super(message);
    }

}
