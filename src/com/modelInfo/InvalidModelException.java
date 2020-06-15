package com.modelInfo;

/**
 * Class representing invalid model exception.
 * It is thrown when the provided model name or bodyType of the Model is incorrect.
 */
public class InvalidModelException extends Exception {
    public InvalidModelException(String message) {
        super(message);
    }
}
