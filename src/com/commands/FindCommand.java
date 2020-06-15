package com.commands;

import com.commands.commandControls.Command;
import com.commands.commandControls.InvalidVinException;
import com.database.QueryExecutor;

import java.util.List;

/**
 * Class implementing Command interface
 * which provides the functionality to find a list
 * with vehicles info by emission standard.
 */
public class FindCommand implements Command {
    private QueryExecutor queryExecutor;

    public FindCommand(QueryExecutor queryExecutor) {
        this.queryExecutor = queryExecutor;
    }

    /**
     * Method checks whether the factory is empty, then if not
     * it returns the list of all the vehicles which have the
     * specified emission standard.
     *
     * @param input contains the emission standard to be found
     * @return list with info of the vehicles found with the specified emission standard
     * @throws InvalidVinException
     */
    @Override
    public String executeCommand(String input) throws InvalidVinException {
        List<String> getVins = queryExecutor.getAllVins();
        if (getVins.size() == 0) {
            throw new InvalidVinException("Empty factory");
        }
        return queryExecutor.findAll(input);
    }
}
