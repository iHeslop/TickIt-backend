package io.nology.tickitbackend.exceptions;

import org.springframework.http.HttpStatus;

public class NotFoundException extends Exception {
    private static final HttpStatus statusCode = HttpStatus.NOT_FOUND;

    public <T> NotFoundException(Class<T> entityType, Long id) {
        super(String.format("Could not find %s with id %s", entityType.getSimpleName(), id));
    }

    public static HttpStatus getStatuscode() {
        return statusCode;
    }
}
