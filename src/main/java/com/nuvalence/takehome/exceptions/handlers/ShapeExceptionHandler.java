package com.nuvalence.takehome.exceptions.handlers;

import com.nuvalence.takehome.exceptions.InvalidArgumentsException;
import com.nuvalence.takehome.exceptions.UnprocessableArgumentsException;
import com.nuvalence.takehome.models.response.ExceptionBody;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ShapeExceptionHandler extends ResponseEntityExceptionHandler{

    @ExceptionHandler(value = {InvalidArgumentsException.class})
    protected ResponseEntity<Object> handleParamsInvalidException(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(ex,
                new ExceptionBody("Internal issue and will be handled by the developers.", ex.getMessage()),
                new HttpHeaders(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                request);
    }

    @ExceptionHandler(value = {UnprocessableArgumentsException.class})
    protected ResponseEntity<Object> handleInternalParamsInvalidException(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(ex,
                new ExceptionBody("Please change your values for the rectangles.", ex.getMessage()),
                new HttpHeaders(),
                HttpStatus.UNPROCESSABLE_ENTITY,
                request);
    }
}
