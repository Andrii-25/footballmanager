package com.andrii.footballmanager.exception;

public class TeamNotFoundException extends Exception{
    public TeamNotFoundException(String message) {
        super(message);
    }
}
