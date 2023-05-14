package com.example.playground.common.exception;


import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ResourceConflictException extends GeneralResponseException {
    private final ProblemDetail problemDetail;

    public ResourceConflictException(Class c, @NotNull String name) {
        problemDetail = ProblemDetail.forStatus(HttpStatus.CONFLICT);
        problemDetail.setTitle(String.format("%s already exists!", c.getSimpleName()));
        problemDetail.setDetail(String.format("%s with name %s already exists!",c.getSimpleName(), name));
    }

    @Override
    public ProblemDetail getProblemDetail() {
        return problemDetail;
    }
}
