package com.engineInfo;

/**
 * Class representing invalid engine standard exception.
 * It is thrown when the provided engine or engine component is incorrect.
 */
public class InvalidEngineException extends Exception {
    public InvalidEngineException(String message) {
        super(message);
    }
}
