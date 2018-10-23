package com.sda.SpringFacebook.exceptions;

public class EventNotExistException extends RuntimeException {

    public EventNotExistException(String message) {
        super(message);
    }
}
