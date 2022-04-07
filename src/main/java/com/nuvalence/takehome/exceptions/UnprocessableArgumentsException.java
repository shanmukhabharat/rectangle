package com.nuvalence.takehome.exceptions;

public class UnprocessableArgumentsException extends RuntimeException{
    public UnprocessableArgumentsException(String message) {
        super(message);
    }
}
