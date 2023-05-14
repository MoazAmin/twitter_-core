package com.example.playground.common.exception;


import org.springframework.http.ProblemDetail;

public abstract class GeneralResponseException extends RuntimeException {
    public abstract ProblemDetail getProblemDetail();
}
