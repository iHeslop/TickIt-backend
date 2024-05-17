package io.nology.tickitbackend.exceptions;

import org.springframework.http.HttpStatus;

public class BadRequestException extends Exception {
    private static final HttpStatus statusCode = HttpStatus.BAD_REQUEST;
    private String message;

    public BadRequestException(String message) {
        super(message);
        this.message = message;
    }

    public static HttpStatus getStatuscode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }

}
