package com.commands.commandControls;

/**
 * Class representing invalid command exception.
 * It is thrown when the following command or input is incorrect.
 */
public class InvalidCommandException extends Exception {
    public InvalidCommandException(String message) {
        super(message);
    }
}
