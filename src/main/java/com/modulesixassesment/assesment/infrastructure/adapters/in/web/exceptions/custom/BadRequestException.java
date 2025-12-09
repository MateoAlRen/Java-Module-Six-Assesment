package com.modulesixassesment.assesment.infrastructure.adapters.in.web.exceptions.custom;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
