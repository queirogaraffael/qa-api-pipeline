package com.example.cinema.api.exceptions;

public class NumeroDeQuartoJaCadastradoException extends RuntimeException{
    public NumeroDeQuartoJaCadastradoException() {
    }

    public NumeroDeQuartoJaCadastradoException(String message) {
        super(message);
    }
}
