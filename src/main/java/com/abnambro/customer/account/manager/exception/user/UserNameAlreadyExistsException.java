package com.abnambro.customer.account.manager.exception.user;


import com.abnambro.customer.account.manager.exception.AlreadyException;

import java.io.Serial;

/**
 * Exception class named {@link UserNameAlreadyExistsException} thrown when an email already exists in the system.
 */
public class UserNameAlreadyExistsException extends AlreadyException {

    @Serial
    private static final long serialVersionUID = -435147781709055915L;

    private static final String DEFAULT_MESSAGE =
            "The specified email already exists";

    private static final String MESSAGE_TEMPLATE =
            "Username already exists: ";

    public UserNameAlreadyExistsException(String email) {
        super(MESSAGE_TEMPLATE.concat(email));
    }

    public UserNameAlreadyExistsException() {
        super(DEFAULT_MESSAGE);
    }

}
