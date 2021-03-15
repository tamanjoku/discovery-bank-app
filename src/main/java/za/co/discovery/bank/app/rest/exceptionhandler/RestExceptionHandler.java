package za.co.discovery.bank.app.rest.exceptionhandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import za.co.discovery.bank.app.enums.ResponseEnum;
import za.co.discovery.bank.app.exception.AtmNotFundedException;
import za.co.discovery.bank.app.exception.BankAppException;
import za.co.discovery.bank.app.exception.DataNotFoundException;
import za.co.discovery.bank.app.exception.InvalidWithdrawalAccountException;
import za.co.discovery.bank.app.exception.LimitExceededException;
import za.co.discovery.bank.app.rest.response.GenericWSDataHolderResponse;
import za.co.discovery.bank.app.rest.response.ValidationError;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class handles exceptions thrown for various exception classes and builds the
 * REST response for these exceptions
 * @author Torti Ama-Njoku @ Discovery
 */
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;
    
    /**
     * REST webservice exception handler response for the {@link BankAppException}
     * exception.
     * @param ex exception occurred
     * @param request servlet request
     * @return {@link ResponseEntity} HTTP response entity with JSON response attributes
     */
    @ExceptionHandler({DataNotFoundException.class, AtmNotFundedException.class, InvalidWithdrawalAccountException.class,
            LimitExceededException.class, LimitExceededException.class, AtmNotFundedException.class})
    public ResponseEntity<?> handleAlreadyExistsException(BankAppException ex, HttpServletRequest request) {
        return new ResponseEntity<>(buildResponse(ex), null, ex.getExceptionResponse().getHttpCode());
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException manve,
            HttpHeaders headers, HttpStatus status, WebRequest request) {

        GenericWSDataHolderResponse errorDetail = new GenericWSDataHolderResponse();
        errorDetail.setTimeStamp(new Date().getTime());
        errorDetail.setStatus(status.value());
        errorDetail.setResponseCode(ResponseEnum.RESPONSE_CODE_101.getCode());
        errorDetail.setResponseMessage(
                messageSource.getMessage(ResponseEnum.RESPONSE_CODE_101.getLabelKey(), 
                        null, request.getLocale()));
        errorDetail.setDeveloperMessage(manve.getClass().getName());

        // Create ValidationError instances
        List<FieldError> fieldErrors = manve.getBindingResult().getFieldErrors();
        for (FieldError fe : fieldErrors) {

            List<ValidationError> validationErrorList = errorDetail.getErrors().get(fe.getField());
            if (validationErrorList == null) {
                validationErrorList = new ArrayList<>();
                errorDetail.getErrors().put(fe.getField(), validationErrorList);
            }
            ValidationError validationError = new ValidationError();
            validationError.setCode(fe.getCode());
            validationError.setMessage(messageSource.getMessage(fe, null));
            validationErrorList.add(validationError);
        }
        return handleExceptionInternal(manve, errorDetail, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {

        GenericWSDataHolderResponse errorDetail = new GenericWSDataHolderResponse();
        errorDetail.setTimeStamp(new Date().getTime());
        errorDetail.setStatus(status.value());
        errorDetail.setResponseCode(ResponseEnum.RESPONSE_CODE_101.getCode());
        errorDetail.setResponseMessage(ex.getMessage());
        errorDetail.setDeveloperMessage(ex.getClass().getName());

        return handleExceptionInternal(ex, errorDetail, headers, status, request);
    }

    private GenericWSDataHolderResponse buildResponse(BankAppException ex) {
        GenericWSDataHolderResponse errorDetail = new GenericWSDataHolderResponse();
        errorDetail.setTimeStamp(new Date().getTime());
        errorDetail.setStatus(ex.getExceptionResponse().getHttpCode().value());
        errorDetail.setResponseMessage(ex.getMessage());
        errorDetail.setResponseCode(ex.getExceptionResponse().getCode());
        errorDetail.setDeveloperMessage(ex.getClass().getName());

        return errorDetail;
    }
}
