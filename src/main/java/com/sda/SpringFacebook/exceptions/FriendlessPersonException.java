package com.sda.SpringFacebook.exceptions;

public class FriendlessPersonException extends RuntimeException {

    public FriendlessPersonException(String message) {
        super(message);
    }
}
