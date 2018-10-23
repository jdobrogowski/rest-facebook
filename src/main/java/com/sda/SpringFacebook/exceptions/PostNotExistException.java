package com.sda.SpringFacebook.exceptions;

public class PostNotExistException extends RuntimeException {

    public PostNotExistException(String message) {
        super(message);
    }
}
