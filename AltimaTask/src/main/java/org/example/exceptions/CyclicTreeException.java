package org.example.exceptions;

public class CyclicTreeException extends RuntimeException{

    public CyclicTreeException() {
        super();
    }

    public CyclicTreeException(String message) {
        super(message);
    }

    public CyclicTreeException(String message, Throwable cause) {
        super(message, cause);
    }

}
