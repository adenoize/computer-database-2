package main.java.com.excilys.cdb.service;

/**
 * @author Aurelien Denoize
 *
 */
public class DatabaseException extends Exception {

    private static final long serialVersionUID = -5049760276954722227L;
    /**
     * Default constructor.
     */
    public DatabaseException() {
    }

    /**
     * @param message message for the exception
     */
    public DatabaseException(String message) {
        super(message);
    }

    /**
     * @param cause cause of the exception
     */
    public DatabaseException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message message for the exception
     * @param cause cause of the exception
     */
    public DatabaseException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message message for the exception
     * @param cause cause of the exception
     * @param enableSuppression enableSuppression of the exception
     * @param writableStackTrace writableStackTrace of the exception
     */
    public DatabaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
