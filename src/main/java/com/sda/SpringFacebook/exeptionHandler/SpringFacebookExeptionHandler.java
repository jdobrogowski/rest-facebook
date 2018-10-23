package com.sda.SpringFacebook.exeptionHandler;

import com.sda.SpringFacebook.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class SpringFacebookExeptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = EventNotExistException.class)
    public ResponseEntity<Object> handleEventNotExistException(WebRequest webRequest, EventNotExistException e) {

        return handleExceptionInternal(e, e.getMessage(), null, HttpStatus.NOT_FOUND, webRequest);
    }

    @ExceptionHandler(value = FriendlessPersonException.class)
    public ResponseEntity<Object> handleFriendlessPersonException(WebRequest webRequest, FriendlessPersonException e) {

        return handleExceptionInternal(e, e.getMessage(), null, HttpStatus.NOT_FOUND, webRequest);
    }

    @ExceptionHandler(value = PasswordNotCorrectException.class)
    public ResponseEntity<Object> handlePasswordNotCorrectException(WebRequest webRequest, PasswordNotCorrectException e) {

        return handleExceptionInternal(e, e.getMessage(), null, HttpStatus.NOT_FOUND, webRequest);
    }

    @ExceptionHandler(value = PhraseNotCorrectException.class)
    public ResponseEntity<Object> handlePhraseNotCorrectException(WebRequest webRequest, PhraseNotCorrectException e) {

        return handleExceptionInternal(e, e.getMessage(), null, HttpStatus.NOT_FOUND, webRequest);
    }

    @ExceptionHandler(value = UserNotExistException.class)
    public ResponseEntity<Object> handleUserNotExistException(WebRequest webRequest, UserNotExistException e) {

        return handleExceptionInternal(e, e.getMessage(), null, HttpStatus.NOT_FOUND, webRequest);
    }

    @ExceptionHandler(value = NoAccessToThisOperationException.class)
    public ResponseEntity<Object> handleUNoAccessToThisOperationException(WebRequest webRequest, NoAccessToThisOperationException e) {

        return handleExceptionInternal(e, e.getMessage(), null, HttpStatus.NOT_FOUND, webRequest);
    }

}
