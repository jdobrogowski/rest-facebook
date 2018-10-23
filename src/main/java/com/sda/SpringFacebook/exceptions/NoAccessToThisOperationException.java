package com.sda.SpringFacebook.exceptions;

public class NoAccessToThisOperationException extends RuntimeException {

    public NoAccessToThisOperationException(String message) {
        super(message);
    }
}
