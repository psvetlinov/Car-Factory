package com.commands.deleteCommand;

import com.FactoryInfo;
import com.commands.commandControls.Command;
import com.commands.commandControls.InvalidVinException;
import com.database.QueryExecutor;

import java.util.List;

/**
 * DeleteCommand class implements the Command interface.
 * Provides the ability to delete a vehicle by provided VIN.
 */
public abstract class DeleteCommand implements Command {
    private FactoryInfo factoryInfo;
    private QueryExecutor queryExecutor;

    public DeleteCommand(FactoryInfo factoryInfo, QueryExecutor queryExecutor) {
        this.factoryInfo = factoryInfo;
        this.queryExecutor = queryExecutor;
    }

    /**
     * This method provides functionality of deleting a vehicle by its VIN.
     * By simple query to the DB, if there is a vehicle with the provided VIN
     * and it is not deleted already, then it is disassembled.
     * @param input contains the command input info after the command name
     * @return Success if the vehicle is removed.
     * @throws InvalidVinException if it is removed already or the VIN is incorrect.
     */
    @Override
    public String executeCommand(String input) throws InvalidVinException {
        List<String> vinList = queryExecutor.getCreatedVehiclesVins();
        if (vinList.contains(input) && !queryExecutor.getStatus(input).equals("D")) {
            queryExecutor.delete(input);
            return "Success";
        }
        throw new InvalidVinException("Invalid VIN or already deleted vehicle");
    }
}
