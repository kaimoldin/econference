package com.qd.econference.exceptions;

import java.math.BigInteger;
import java.util.NoSuchElementException;

public class RoomNotFoundException extends NoSuchElementException {
    public RoomNotFoundException(BigInteger roomId) {
        super("room by id: " + roomId);
    }
}
