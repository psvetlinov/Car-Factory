package com.commands.commandControls;

/**
 * Class representing invalid VIN exception.
 * It is thrown when the provided vehicle VIN is incorrect"
 */
public class InvalidVinException extends Exception {
    public InvalidVinException(String message) {
        super(message);
    }
}
