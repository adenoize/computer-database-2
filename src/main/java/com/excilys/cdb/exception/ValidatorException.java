package main.java.com.excilys.cdb.exception;

/**
 * @author Aurelien Denoize Excilys 2018
 */
public class ValidatorException extends Exception {

    private static final long serialVersionUID = 2256430978892761530L;

    /**
     * Constructor of ValidatorException.
     */
    public ValidatorException() {
        super();
    }

    /**
     * Constructor of ValidatorException with message.
     * @param message the exception message
     */
    public ValidatorException(String message) {
        super(message);
    }
}
