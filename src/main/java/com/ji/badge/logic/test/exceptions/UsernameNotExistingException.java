package com.ji.badge.logic.test.exceptions;

public class UsernameNotExistingException extends Exception{

    public UsernameNotExistingException(String username) {
        super("The username: "+username+" is not existing");
    }

}
