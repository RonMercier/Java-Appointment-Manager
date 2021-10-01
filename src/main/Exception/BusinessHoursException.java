package main.Exception;

/**
 * @author Ron Mercier - 001406973
 *
 * This is an exception used to handle the validation of appointments during business hours.
 */
public class BusinessHoursException extends Exception{
    /**
     * The BusinessHoursException constructor provides an exception to the user that gives them more information
     * about an error.
     *
     * @param message message
     */
    public BusinessHoursException(String message) {
        super(message);
    }
}
