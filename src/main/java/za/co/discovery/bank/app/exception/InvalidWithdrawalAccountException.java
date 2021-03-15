package za.co.discovery.bank.app.exception;

import za.co.discovery.bank.app.enums.ResponseEnum;

/**
 * Exception class to define exception for when an account cannot be found or cannot be used for withdrawal.
 *
 * @author Torti Ama-Njoku @ Discovery
 */
@SuppressWarnings("serial")
public class InvalidWithdrawalAccountException extends BankAppException {

    /**
     * Default Constructor
     */
    public InvalidWithdrawalAccountException() { }

    /**
     * Constructor to with exception message
     * @param message exception message
     * @param exceptionResponse the response object associated with this exception
     */
    public InvalidWithdrawalAccountException(String message, ResponseEnum exceptionResponse) {
        super(message, exceptionResponse);
    }

    /**
     * Constructor with message and exception cause
     * @param message exception message
     * @param cause exception cause
     * @param exceptionResponse the response object associated with this exception
     */
    public InvalidWithdrawalAccountException(String message, Throwable cause, ResponseEnum exceptionResponse) {
        super(message, cause, exceptionResponse);
    }
}
