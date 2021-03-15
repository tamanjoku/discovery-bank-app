package za.co.discovery.bank.app.rest.response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Generic data holder super class for outgoing REST webservice responses
 * @author Torti Ama-Njoku @ Discovery
 */
public class GenericWSDataHolderResponse {

    private String responseCode;
    private String responseMessage;
    private int status;
    private long timeStamp;
    private String developerMessage;
    private Map<String, List<ValidationError>> errors = new HashMap<>();
    
    /**
     * Default Constructor
     */
    public GenericWSDataHolderResponse() {
        super();
    }
    
    /**
     * @param responseCode webservice response code
     * @param responseMessage webservice response message
     * @param status HTTP status code
     * @param timeStamp time stamp of the reponse
     * @param developerMessage developer's message
     * @param errors any validation errors
     */
    public GenericWSDataHolderResponse(String responseCode, String responseMessage, 
            int status, long timeStamp, String developerMessage, 
            Map<String, List<ValidationError>> errors) {
        super();
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.status = status;
        this.timeStamp = timeStamp;
        this.developerMessage = developerMessage;
        this.errors = errors;
    }

    /**
     * @param responseCode webservice response code
     * @param responseMessage webservice response message
     * @param status HTTP status code
     * @param timeStamp time stamp of the reponse
     */
    public GenericWSDataHolderResponse(String responseCode, String responseMessage, 
            int status, long timeStamp) {
        super();
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.status = status;
        this.timeStamp = timeStamp;
    }

    /**
     * @return the responseCode
     */
    public String getResponseCode() {
        return responseCode;
    }

    /**
     * @param responseCode the responseCode to set
     */
    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    /**
     * @return the responseMessage
     */
    public String getResponseMessage() {
        return responseMessage;
    }

    /**
     * @param responseMessage the responseMessage to set
     */
    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * @return the timeStamp
     */
    public long getTimeStamp() {
        return timeStamp;
    }

    /**
     * @param timeStamp the timeStamp to set
     */
    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    /**
     * @return the developerMessage
     */
    public String getDeveloperMessage() {
        return developerMessage;
    }

    /**
     * @param developerMessage the developerMessage to set
     */
    public void setDeveloperMessage(String developerMessage) {
        this.developerMessage = developerMessage;
    }

    /**
     * @return the errors
     */
    public Map<String, List<ValidationError>> getErrors() {
        return errors;
    }

    /**
     * @param errors the errors to set
     */
    public void setErrors(Map<String, List<ValidationError>> errors) {
        this.errors = errors;
    }
}
