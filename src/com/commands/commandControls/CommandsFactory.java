package com.commands.commandControls;

import com.FactoryInfo;
import com.commands.FindCommand;
import com.commands.createCommand.CreateCarCommand;
import com.commands.createCommand.CreateSuvCommand;
import com.commands.deleteCommand.DeleteCar;
import com.commands.deleteCommand.DeleteSuv;
import com.commands.printCommand.PrintCommand;
import com.commands.printCommand.PrintVinCommand;
import com.database.QueryExecutor;
import com.engineInfo.InvalidEmissionStandardException;
import com.engineInfo.InvalidEngineException;
import com.modelInfo.InvalidModelException;
import com.transmissionInfo.InvalidTransmissionException;

import java.util.HashMap;

/**
 * Class representing a factory for commands.
 * Contains of a commandsMap with all available commands,
 * the factory info, and a QueryExecutor for executing DB queries;
 */
public class CommandsFactory {
    private HashMap<String, Command> commandsMap;
    private FactoryInfo factoryInfo;
    private QueryExecutor queryExecutor;

    public CommandsFactory(FactoryInfo factoryInfo, QueryExecutor queryExecutor) {
        this.factoryInfo = factoryInfo;
        this.queryExecutor = queryExecutor;
        setCommandsMap(factoryInfo, queryExecutor);
    }

    private void setCommandsMap(FactoryInfo factoryInfo, QueryExecutor queryExecutor) {
        commandsMap = new HashMap<>();
        commandsMap.put("create car", new CreateCarCommand(factoryInfo, queryExecutor));
        commandsMap.put("create suv", new CreateSuvCommand(factoryInfo, queryExecutor));
        commandsMap.put("delete car", new DeleteCar(factoryInfo, queryExecutor));
        commandsMap.put("delete suv", new DeleteSuv(factoryInfo, queryExecutor));
        commandsMap.put("print vin", new PrintVinCommand(queryExecutor));
        commandsMap.put("print all", new PrintCommand(queryExecutor));
        commandsMap.put("find", new FindCommand(queryExecutor));

    }

    /**
     * Method that controls the execution of the commands.
     *
     * @param input contains the full command input
     * @return the output of executing the specified command
     * @throws InvalidModelException
     * @throws InvalidTransmissionException
     * @throws InvalidEngineException
     * @throws InvalidVinException
     * @throws InvalidEmissionStandardException
     * @throws InvalidCommandException
     */
    public String interpretCommand(String input) throws InvalidModelException, InvalidTransmissionException, InvalidEngineException, InvalidVinException, InvalidEmissionStandardException, InvalidCommandException {
        CommandController commandController = new CommandControllerImpl();

        String[] buffer = input.split(" ", 2);
        if (buffer.length < 2) {
            throw new InvalidCommandException("Invalid command");
        }

        String commandStartName = buffer[0];
        String commandInfo = buffer[1];

        String output = "";

        switch (commandStartName) {
            case "create":
                output = commandController.getCreateCommand(commandInfo, commandsMap);
                break;

            case "delete":
                output = commandController.getDeleteCommand(commandInfo, commandsMap);
                break;

            case "print":
                output = commandController.getPrintCommand(commandInfo, commandsMap);
                break;

            case "find":
                output = commandController.getFindCommand(commandInfo, commandsMap);
                break;
        }
        return output;
    }
}
