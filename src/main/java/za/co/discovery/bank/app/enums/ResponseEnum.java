package za.co.discovery.bank.app.enums;

import org.springframework.http.HttpStatus;

/**
 * Holds all response codes, both errors and successful
 * 
 * @author Torti Ama-Njoku @ Discovery
 */
public enum ResponseEnum {

    /**
     * When a request is successful
     */
    SUCCESSFUL("00", "request.successful", HttpStatus.OK),
    /**
     * For an unexpected error
     */
    RESPONSE_CODE_100("100", "unexpectedError", HttpStatus.INTERNAL_SERVER_ERROR),
    /**
     * For when rest parameters fail validation
     */
    RESPONSE_CODE_101("101", "method.argument.not.valid", HttpStatus.BAD_REQUEST),
    
    //-------ATM responses - 110 - 119
    /**
     * When here are no client accounts to be displayed
     */
    RESPONSE_CODE_110("110", "no.client.accounts", HttpStatus.NOT_FOUND),
    /**
     * When an atm could not be found
     */
    RESPONSE_CODE_111("111", "atm.not.found", HttpStatus.NOT_FOUND),
    /**
     * When an atm is not funded
     */
    RESPONSE_CODE_112("112", "atm.not.funded", HttpStatus.INTERNAL_SERVER_ERROR),
    /**
     * When the account number could not be found
     */
    RESPONSE_CODE_113("113", "withdrawal.account.invalid", HttpStatus.NOT_FOUND),
    /**
     * When the account's maximum withdrwal limit has been exceeded
     */
    RESPONSE_CODE_114("114", "withdrawal.max.limit.exceeded", HttpStatus.BAD_REQUEST),
    /**
     * When the minimum withdrwal limit has been exceeded
     */
    RESPONSE_CODE_115("115", "withdrawal.min.limit.exceeded", HttpStatus.BAD_REQUEST),
    /**
     * When the atm does not have enough of the funds the client has requested for.
     */
    RESPONSE_CODE_116("116", "atm.not.sufficiently.funded", HttpStatus.BAD_REQUEST),;

    private final String code;
    private final String labelKey;
    private final HttpStatus httpCode;
    
    /**
     * @param code error code
     * @param labelKey error display label key
     * @param httpCode error http code
     */
    ResponseEnum(String code, String labelKey, HttpStatus httpCode) {
        this.code = code;
        this.labelKey = labelKey;
        this.httpCode = httpCode;
    }
    
    /**
     * @return the error code
     */
    public String getCode() {
        return code;
    }

    /**
     * @return the error display label key
     */
    public String getLabelKey() {
        return labelKey;
    }

    /**
     * @return the returned HTTP code
     */
    public HttpStatus getHttpCode() {
        return httpCode;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("ResponseEnum");
        sb.append("{code='").append(code).append('\'');
        sb.append(", labelKey='").append(labelKey).append('\'');
        sb.append(", httpCode='").append(httpCode).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
