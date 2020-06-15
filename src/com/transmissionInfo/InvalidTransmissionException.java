package com.transmissionInfo;

/**
 * Class representing invalid transmission exception.
 * It is thrown when the provided transmission type or gears are incorrect.
 */
public class InvalidTransmissionException extends Exception {
    public InvalidTransmissionException(String message) {
        super(message);
    }
}
