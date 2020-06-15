package com.engineInfo;

/**
 * Class representing invalid emission standard exception.
 * It is thrown when the provided emission standard is incorrect.
 */
public class InvalidEmissionStandardException extends Exception {
    public InvalidEmissionStandardException(String message) {
        super(message);
    }
}
