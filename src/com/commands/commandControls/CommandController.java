package com.commands.commandControls;

import com.engineInfo.InvalidEmissionStandardException;
import com.engineInfo.InvalidEngineException;
import com.modelInfo.InvalidModelException;
import com.transmissionInfo.InvalidTransmissionException;

import java.util.HashMap;

/**
 * Interface representing functionality of getting commands from command factory
 * and executing them directly.
 */
public interface CommandController {
    /**
     * @param commandInfo contains the command second option and command info after the create command
     * @param commandsMap contains the map of all available commands in the command factory
     * @return the execution of the command if it is present
     */
    String getCreateCommand(String commandInfo, HashMap<String, Command> commandsMap) throws InvalidEngineException,
            InvalidTransmissionException, InvalidVinException, InvalidEmissionStandardException, InvalidModelException, InvalidCommandException;

    /**
     * @param commandInfo contains the command second option and command info after the delete command
     * @param commandsMap contains the map of all available commands in the command factory
     * @return the execution of the command if it is present
     */
    String getDeleteCommand(String commandInfo, HashMap<String, Command> commandsMap) throws InvalidEngineException,
            InvalidTransmissionException, InvalidVinException, InvalidEmissionStandardException, InvalidModelException, InvalidCommandException;

    /**
     * @param commandInfo contains the command second option and command info after the print command
     * @param commandsMap contains the map of all available commands in the command factory
     * @return the execution of the command if it is present
     */
    String getPrintCommand(String commandInfo, HashMap<String, Command> commandsMap) throws InvalidEngineException,
            InvalidTransmissionException, InvalidVinException, InvalidEmissionStandardException, InvalidModelException, InvalidCommandException;

    /**
     * @param commandInfo contains the command command info(emission standard) to be executed after the find command
     * @param commandMap  contains the map of all available commands in the command factory
     * @return the execution of the command if it is present
     */
    String getFindCommand(String commandInfo, HashMap<String, Command> commandMap) throws InvalidEngineException, InvalidTransmissionException, InvalidVinException, InvalidEmissionStandardException, InvalidModelException, InvalidCommandException;

}
