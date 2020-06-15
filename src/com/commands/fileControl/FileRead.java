package com.commands.fileControl;

import com.commands.commandControls.CommandsFactory;

/**
 * Interface providing functionality to read queries sequentially
 * from file.
 */
public interface FileRead {
    /**
     * Method that reads and executes the queries in the file.
     *
     * @param fileName        is the specified file name
     * @param commandsFactory is used to invoke interpretCommand to execute the queries
     * @return the output of the executed commands
     */
    String readQueriesFromFile(String fileName, CommandsFactory commandsFactory);
}
