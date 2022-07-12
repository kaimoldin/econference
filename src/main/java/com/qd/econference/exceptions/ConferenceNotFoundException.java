package com.qd.econference.exceptions;

import java.math.BigInteger;
import java.util.NoSuchElementException;

public class ConferenceNotFoundException extends NoSuchElementException {
    public ConferenceNotFoundException(BigInteger conferenceId) {
        super("conference by id: " + conferenceId);
    }
}
