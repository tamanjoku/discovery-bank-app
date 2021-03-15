package za.co.discovery.bank.app.rest.response;

/**
 * REST webservice validation error response class
 * @author Torti Ama-Njoku @ Discovery
 */
public class ValidationError {

    private String code;
    private String message;
    
    /**
     * Default Constructor
     */
    public ValidationError() {
        super();
    }
    
    /**
     * @param code error code
     * @param message error display message
     */
    public ValidationError(String code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
