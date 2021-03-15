package za.co.discovery.bank.app.exception;

import za.co.discovery.bank.app.enums.ResponseEnum;

/**
 * Exception class to define exception for when an atm is not allocated funds for dispensing.
 *
 * @author Torti Ama-Njoku @ Discovery
 */
@SuppressWarnings("serial")
public class AtmNotFundedException extends BankAppException {

    /**
     * Default Constructor
     */
    public AtmNotFundedException() { }

    /**
     * Constructor to with exception message
     * @param message exception message
     * @param exceptionResponse the response object associated with this exception
     */
    public AtmNotFundedException(String message, ResponseEnum exceptionResponse) {
        super(message, exceptionResponse);
    }

    /**
     * Constructor with message and exception cause
     * @param message exception message
     * @param cause exception cause
     * @param exceptionResponse the response object associated with this exception
     */
    public AtmNotFundedException(String message, Throwable cause, ResponseEnum exceptionResponse) {
        super(message, cause, exceptionResponse);
    }
}
