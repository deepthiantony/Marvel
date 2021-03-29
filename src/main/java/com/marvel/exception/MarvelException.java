package com.marvel.exception;


public class MarvelException extends RuntimeException {

    public MarvelException() {
        super();
    }

    public MarvelException(String message) {
        super(message);
    }

    public MarvelException(String message, Throwable cause) {
        super(message, cause);
    }
}
