package za.co.discovery.bank.app.exception;

import za.co.discovery.bank.app.enums.ResponseEnum;

/**
 * Exception super class.
 *
 * @author Torti Ama-Njoku @ Discovery
 */
@SuppressWarnings("serial")
public class BankAppException extends Exception {
    
    private ResponseEnum exceptionResponse;
    
    /**
     * Default Constructor
     */
    public BankAppException() { }

    /**
     * Constructor to with exception message
     * @param message exception message
     * @param exceptionResponse the response object associated with this exception
     */
    public BankAppException(String message, ResponseEnum exceptionResponse) {
        super(message);
        this.exceptionResponse = exceptionResponse;
    }

    /**
     * Constructor with message and exception cause
     * @param message exception message
     * @param cause exception cause
     * @param exceptionResponse the response object associated with this exception
     */
    public BankAppException(String message, Throwable cause, ResponseEnum exceptionResponse) {
        super(message, cause);
        this.exceptionResponse = exceptionResponse;
    }

    /**
     * @return the exceptionResponse
     */
    public ResponseEnum getExceptionResponse() {
        return exceptionResponse;
    }

    /**
     * @param exceptionResponse the exceptionResponse to set
     */
    public void setExceptionResponse(ResponseEnum exceptionResponse) {
        this.exceptionResponse = exceptionResponse;
    }
}