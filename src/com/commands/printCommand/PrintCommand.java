package com.commands.printCommand;

import com.commands.commandControls.Command;
import com.commands.commandControls.InvalidVinException;
import com.database.QueryExecutor;

import java.util.List;

/**
 * Class implementing Command interface
 * providing the ability to print all the vehicles in the Factory.
 */
public class PrintCommand implements Command {
    private QueryExecutor queryExecutor;

    public PrintCommand(QueryExecutor queryExecutor) {
        this.queryExecutor = queryExecutor;
    }

    /**
     * The method checks if there are vehicles in the Factory DB
     * then if factory is not emtpy it returns the list of the vehicles info
     * through a queryExecutor DB query method printAll().
     *
     * @param input contains the command input info after the command name
     * @return list with info of all the vehicles in the Factory.
     * @throws InvalidVinException
     */
    @Override
    public String executeCommand(String input) throws InvalidVinException {
        List<String> getVins = queryExecutor.getAllVins();
        if (getVins.size() == 0) {
            throw new InvalidVinException("Empty factory");
        }
        return queryExecutor.printAll();
    }
}
