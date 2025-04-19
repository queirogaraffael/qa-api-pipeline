package com.example.cinema.api.exceptions;

public class GeneroJaExisteException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public GeneroJaExisteException(String message) {
        super(message);
    }
}
