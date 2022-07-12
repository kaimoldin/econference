package com.qd.econference.exceptions;

import java.util.NoSuchElementException;

public class RoleNotFoundException extends NoSuchElementException {
    public RoleNotFoundException(String roleName) {
        super("role by name: " + roleName);
    }
}
