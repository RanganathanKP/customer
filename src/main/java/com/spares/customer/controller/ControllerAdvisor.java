package com.spares.customer.controller;

import com.spares.customer.DTO.ErrorDTO;
import com.spares.customer.exception.CustomerException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {




    @ExceptionHandler(CustomerException.class)
    public ResponseEntity<Object> handleUserNotFoundException(
            CustomerException ex, WebRequest request) {
        logger.info(ex);
        ErrorDTO error= new ErrorDTO("Error",ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.OK);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleOtherException(
            Exception ex, WebRequest request) {
        logger.info(ex);
        ErrorDTO error= new ErrorDTO("SYSTEM","SYSTEM ERROR");
        return new ResponseEntity<>(error, HttpStatus.OK);
    }

}
