package com.commands.printCommand;

import com.commands.commandControls.Command;
import com.commands.commandControls.InvalidVinException;
import com.database.QueryExecutor;

import java.util.List;

/**
 * Class implementing Command interface
 * providing the ability to print vehicle info by provided VIN.
 */
public class PrintVinCommand implements Command {
    private QueryExecutor queryExecutor;

    public PrintVinCommand(QueryExecutor queryExecutor) {
        this.queryExecutor = queryExecutor;
    }

    /**
     * The method first checks if the Factory contains a vehicle
     * with the provided VIN, then if true it shows the vehicle info.
     *
     * @param input contains the command input info after the command name
     * @return
     * @throws InvalidVinException
     */
    @Override
    public String executeCommand(String input) throws InvalidVinException {
        List<String> vinList = queryExecutor.getAllVins();
        if (!vinList.contains(input)) {
            throw new InvalidVinException("No vehicle with this VIN");
        } else {
            return queryExecutor.print(input);
        }
    }
}
