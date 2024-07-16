package com.knowit.projectService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) throws Exception{
        CustomizedExceptionResponse customizedExceptionResponse = new CustomizedExceptionResponse(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        ResponseEntity<Object> responseEntity = new ResponseEntity<>(customizedExceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        return responseEntity;
    }

    @ExceptionHandler(ProjectNotFoundException.class)
    public final ResponseEntity<Object> handleProjectNotFoundException(Exception ex, WebRequest request) throws Exception{
        CustomizedExceptionResponse customizedExceptionResponse = new CustomizedExceptionResponse(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        ResponseEntity<Object> responseEntity = new ResponseEntity<>(customizedExceptionResponse, HttpStatus.NOT_FOUND);
        return responseEntity;
    }
}
