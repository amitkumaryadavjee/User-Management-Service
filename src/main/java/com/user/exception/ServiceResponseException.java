package com.user.exception;


import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ServiceResponseException extends RuntimeException {
    private HttpStatus httpStatus;
    private String message;

    public ServiceResponseException(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    private ServiceResponseException(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public static ServiceResponseException badRequest() {
        return new ServiceResponseException(HttpStatus.BAD_REQUEST);
    }

    public static ServiceResponseException internalServerError() {
        return new ServiceResponseException(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ServiceResponseException message(String message) {
        this.message = message;
        return this;
    }
}

