package com.chegg.userauth.model.exceptions;

public class RunTimeException extends Exception{
    public static class UserNotFoundException extends RuntimeException {
        public UserNotFoundException(String message) {
            super(message);
        }
    }

    public static class IncorrectPasswordException extends RuntimeException {
        public IncorrectPasswordException(String message) {
            super(message);
        }
    }

}
