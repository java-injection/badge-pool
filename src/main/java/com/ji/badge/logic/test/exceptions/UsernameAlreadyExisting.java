package com.ji.badge.logic.test.exceptions;

public class UsernameAlreadyExisting extends Exception{

    public UsernameAlreadyExisting(String username) {
        super("The user ["+username+"] is already existing." );
    }
}