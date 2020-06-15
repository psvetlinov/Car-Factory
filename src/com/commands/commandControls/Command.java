package com.commands.commandControls;

import com.engineInfo.InvalidEmissionStandardException;
import com.engineInfo.InvalidEngineException;
import com.modelInfo.InvalidModelException;
import com.transmissionInfo.InvalidTransmissionException;

/**
 *Command interface having executeCommand method
 * which interprets commands from the command line
**/
public interface Command {
    /**
     * @param input contains the command input info after the command name
     * @return the output of the executed command
    **/
    String executeCommand(String input) throws InvalidModelException, InvalidEngineException, InvalidTransmissionException, InvalidVinException, InvalidEmissionStandardException, InvalidCommandException;
}
