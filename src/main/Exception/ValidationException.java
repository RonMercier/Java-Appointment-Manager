package main.Exception;

/**
 * @author Ron Mercier - 001406973
 *
 *
 * The ValidationException inherits from Exception class.
 */
public class ValidationException extends Exception {
    /**
     * This method passes the error string to the Exception class which handles all the functionality
     * of the exception
     * .
     * @param s message
     */
    public ValidationException(String s) {
        super(s);
    }
}