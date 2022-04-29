package com.flowers.users.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created with IntelliJ IDEA.
 * User: Pankaj
 * Date: 4/27/22
 * Time: 7:25 PM
 * To change this template use File | Settings | File Templates.
 */
@ControllerAdvice
public class UserExceptionController {
    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<String> exception(UserNotFoundException exception) {
        return new ResponseEntity<>("User list is empty", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = UserUpdateDetailsNotFound.class)
    public ResponseEntity<String> exceptionForUpdateDetails(UserUpdateDetailsNotFound exception) {
        return new ResponseEntity<>("User list is empty", HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<String> userListIsEmpty(Exception exception) {
        return new ResponseEntity<>("input user ID is not valid", HttpStatus.NOT_FOUND);
    }
}