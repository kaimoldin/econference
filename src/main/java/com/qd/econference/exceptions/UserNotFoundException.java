package com.qd.econference.exceptions;

import java.math.BigInteger;
import java.util.NoSuchElementException;

public class UserNotFoundException extends NoSuchElementException {
    public UserNotFoundException(BigInteger userId) {
        super("user by id: " + userId);
    }
}
