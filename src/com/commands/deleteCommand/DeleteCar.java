package com.commands.deleteCommand;

import com.FactoryInfo;
import com.database.QueryExecutor;

public class DeleteCar extends DeleteCommand {
    public DeleteCar(FactoryInfo factoryInfo, QueryExecutor queryExecutor) {
        super(factoryInfo, queryExecutor);
    }
}
