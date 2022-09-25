package org.example.exceptions;

public class FamilyTreeNodeNotFoundException extends RuntimeException{

    public FamilyTreeNodeNotFoundException() {
        super();
    }

    public FamilyTreeNodeNotFoundException(String message) {
        super(message);
    }

    public FamilyTreeNodeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
