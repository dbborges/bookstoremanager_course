package com.danielborges.bookstoremanager.users.exception;

import javax.persistence.EntityExistsException;

public class UserAlreadyExistsexception extends EntityExistsException {
    public UserAlreadyExistsexception(String email, String username) {
        super(String.format("User with email %s or username %s already exists!", email, username));
    }
}
