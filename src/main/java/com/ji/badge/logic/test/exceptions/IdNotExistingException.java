package com.ji.badge.logic.test.exceptions;

public class IdNotExistingException extends Exception{

    public IdNotExistingException(int id) {
        super("The id ["+id+"] is not existing." );
    }
}