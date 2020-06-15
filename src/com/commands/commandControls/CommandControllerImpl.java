package com.commands.commandControls;

import com.engineInfo.InvalidEmissionStandardException;
import com.engineInfo.InvalidEngineException;
import com.modelInfo.InvalidModelException;
import com.transmissionInfo.InvalidTransmissionException;

import java.util.HashMap;

/**
 * Class implementing the methods from CommandController interface.
 * Each method executes the command if it is present in the command
 * factory with a valid command input.
 */
public class CommandControllerImpl implements CommandController {
    /**
     * Method executing create command if the commandInfo is correct
     *
     * @param commandInfo contains the command second option
     *                    and command info after the create command (ex. car model=A2 engine=D...)
     * @param commandsMap contains the map of all available commands in the command factory
     * @return the execution of the command if it is present
     * @throws InvalidEngineException
     * @throws InvalidTransmissionException
     * @throws InvalidVinException
     * @throws InvalidEmissionStandardException
     * @throws InvalidModelException
     * @throws InvalidCommandException
     */
    @Override
    public String getCreateCommand(String commandInfo, HashMap<String, Command> commandsMap) throws InvalidEngineException, InvalidTransmissionException, InvalidVinException, InvalidEmissionStandardException, InvalidModelException, InvalidCommandException {
        String[] cmdBuffer = commandInfo.split(" ", 2);
        String commandAttribute = cmdBuffer[0];
        if (cmdBuffer.length < 2) {
            throw new InvalidCommandException("Invalid command");
        }
        String commandQuery = cmdBuffer[1];
        if (commandAttribute.equals("car")) {
            return commandsMap.get("create " + commandAttribute).executeCommand(commandQuery);
        } else if (commandAttribute.equals("suv")) {
            return commandsMap.get("create " + commandAttribute).executeCommand(commandQuery);
        }
        throw new InvalidCommandException("Invalid Command");
    }

    /**
     * Method executing delete command if the commandInfo is correct
     *
     * @param commandInfo contains the command second option and
     *                    command info after the delete command (ex. car BG0CM44CR3U32Z94Y)
     * @param commandsMap contains the map of all available commands in the command factory
     * @return the execution of the command if it is present
     * @throws InvalidEngineException
     * @throws InvalidTransmissionException
     * @throws InvalidVinException
     * @throws InvalidEmissionStandardException
     * @throws InvalidModelException
     * @throws InvalidCommandException
     */
    @Override
    public String getDeleteCommand(String commandInfo, HashMap<String, Command> commandsMap) throws InvalidEngineException, InvalidTransmissionException, InvalidVinException, InvalidEmissionStandardException, InvalidModelException, InvalidCommandException {
        String[] cmdBuffer = commandInfo.split(" ", 2);
        String commandAttribute = cmdBuffer[0];
        if (cmdBuffer.length < 2) {
            throw new InvalidCommandException("Invalid command");
        }
        String commandQuery = cmdBuffer[1];
        if (commandAttribute.equals("car")) {
            return commandsMap.get("delete " + commandAttribute).executeCommand(commandQuery);
        } else if (commandAttribute.equals("suv")) {
            return commandsMap.get("delete " + commandAttribute).executeCommand(commandQuery);
        }
        throw new InvalidCommandException("Invalid Command");
    }

    /**
     * Method executing print command if the commandInfo is correct
     *
     * @param commandInfo contains the command second option and
     *                    command info after the print command (ex. all or BG0CM44CR3U32Z94Y)
     * @param commandsMap contains the map of all available commands in the command factory
     * @return the execution of the command if it is present
     * @throws InvalidEngineException
     * @throws InvalidTransmissionException
     * @throws InvalidVinException
     * @throws InvalidEmissionStandardException
     * @throws InvalidModelException
     * @throws InvalidCommandException
     */
    @Override
    public String getPrintCommand(String commandInfo, HashMap<String, Command> commandsMap) throws InvalidEngineException, InvalidTransmissionException, InvalidVinException, InvalidEmissionStandardException, InvalidModelException, InvalidCommandException {
        String[] cmdBuffer = commandInfo.split(" ", 2);
        String commandAttribute = cmdBuffer[0];
        String commandQuery;
        if (cmdBuffer.length < 2) {
            if (commandAttribute.equals("all")) {
                commandQuery = "";
                return commandsMap.get("print " + commandAttribute).executeCommand(commandQuery);
            }
            throw new InvalidCommandException("Invalid command");
        }
        if (commandAttribute.equals("vin")) {
            commandQuery = cmdBuffer[1];
            return commandsMap.get("print " + commandAttribute).executeCommand(commandQuery);
        }
        throw new InvalidCommandException("Invalid Command");
    }

    /**
     * Method executing find command if the commandInfo is correct
     *
     * @param commandInfo contains the command command info(emission standard) to be executed after the find command
     * @param commandMap  contains the map of all available commands in the command factory
     * @return the execution of the command if it is present
     * @throws InvalidEngineException
     * @throws InvalidTransmissionException
     * @throws InvalidVinException
     * @throws InvalidEmissionStandardException
     * @throws InvalidModelException
     * @throws InvalidCommandException
     */
    @Override
    public String getFindCommand(String commandInfo, HashMap<String, Command> commandMap) throws InvalidEngineException, InvalidTransmissionException, InvalidVinException, InvalidEmissionStandardException, InvalidModelException, InvalidCommandException {
        return commandMap.get("find").executeCommand(commandInfo);
    }

}
