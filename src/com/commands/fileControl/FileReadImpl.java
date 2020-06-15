package com.commands.fileControl;

import com.MainApp;
import com.commands.commandControls.CommandsFactory;
import com.commands.commandControls.InvalidCommandException;
import com.commands.commandControls.InvalidVinException;
import com.engineInfo.InvalidEmissionStandardException;
import com.engineInfo.InvalidEngineException;
import com.modelInfo.InvalidModelException;
import com.transmissionInfo.InvalidTransmissionException;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class implementing FileRead interface.
 * Providing functionality to read multiple commands sequentially from the file.
 */
public class FileReadImpl implements FileRead {
    /**
     * Method that reads from the file and executes the provided commands in
     * the file sequentially.
     *
     * @param fileName        is the specified file name
     * @param commandsFactory is used to invoke interpretCommand to execute the queries
     * @return the output of the commands execution
     */
    @Override
    public String readQueriesFromFile(String fileName, CommandsFactory commandsFactory) {
        String result = "";
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(fileName)))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                result = commandsFactory.interpretCommand(line);
                System.out.println(result);
            }
        } catch (FileNotFoundException e) {
            Logger.getLogger(MainApp.class.getName()).log(Level.WARNING, e.getMessage());
        } catch (IOException e) {
            Logger.getLogger(MainApp.class.getName()).log(Level.WARNING, e.getMessage());
        } catch (InvalidTransmissionException e) {
            Logger.getLogger(MainApp.class.getName()).log(Level.WARNING, e.getMessage() + " at " + fileName);
        } catch (InvalidVinException e) {
            Logger.getLogger(MainApp.class.getName()).log(Level.WARNING, e.getMessage() + " at " + fileName);
        } catch (InvalidEmissionStandardException e) {
            Logger.getLogger(MainApp.class.getName()).log(Level.WARNING, e.getMessage() + " at " + fileName);
        } catch (InvalidEngineException e) {
            Logger.getLogger(MainApp.class.getName()).log(Level.WARNING, e.getMessage() + " at " + fileName);
        } catch (InvalidModelException e) {
            Logger.getLogger(MainApp.class.getName()).log(Level.WARNING, e.getMessage() + " at " + fileName);
        } catch (InvalidCommandException e) {
            Logger.getLogger(MainApp.class.getName()).log(Level.WARNING, e.getMessage());
        }
        return result;
    }
}
