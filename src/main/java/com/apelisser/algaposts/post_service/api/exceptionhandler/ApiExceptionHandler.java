package com.apelisser.algaposts.post_service.api.exceptionhandler;


import com.apelisser.algaposts.post_service.domain.exception.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ProblemDetail handleEntityNotFoundException(EntityNotFoundException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problemDetail.setTitle("Resource not found");
        String detailMessage = String.format("%s resource not found for parameters: %s",
            ex.getEntityName(),
            ex.getFormattedProperties());

        problemDetail.setDetail(detailMessage);
        problemDetail.setType(URI.create("/error/resource-not-found"));
        return problemDetail;
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleEntityNotFoundException(Exception ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        problemDetail.setTitle("Internal server error");
        problemDetail.setDetail("An error occurred while processing the request.");
        problemDetail.setType(URI.create("/error/server-error"));
        return problemDetail;
    }

}
