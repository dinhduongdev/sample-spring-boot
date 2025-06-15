package com.pdd.exception;


import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({
                MethodArgumentNotValidException.class,
                ConstraintViolationException.class
            })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidationException(Exception ex, WebRequest request) {
        ErrorResponse error = new ErrorResponse();
        error.setTimestamp(new Date());
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setPath(request.getDescription(false).replace("uri=",""));
        error.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());

        String message = ex.getMessage();
        if(ex instanceof MethodArgumentNotValidException) {
            int start = message.lastIndexOf("[");
            int end = message.lastIndexOf("]");
            message = message.substring(start + 1, end-1);
            error.setError("Payload invalid");
        } else if (ex instanceof ConstraintViolationException) {
            message = message.substring(message.indexOf(" ")+1);
            error.setError("Parameter invalid");
        }
        error.setMessage(message);
        return error;
    }


    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleInternalServerError(Exception ex, WebRequest request) {
        ErrorResponse error = new ErrorResponse();
        error.setTimestamp(new Date());
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setPath(request.getDescription(false).replace("uri=",""));
        error.setError(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        if(ex instanceof MethodArgumentTypeMismatchException) {
            error.setMessage("Parameter type mismatch");
        }
        return error;
    }
}
