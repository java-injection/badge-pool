package com.ji.badge.logic.test.exceptions;

public class AttemptSecondLoginException extends Exception{

    public AttemptSecondLoginException(String username) {
        super("The user ["+username+"]" );
    }



}