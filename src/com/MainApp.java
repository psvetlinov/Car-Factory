package com;

import com.commands.commandControls.CommandsFactory;
import com.commands.commandControls.InvalidCommandException;
import com.commands.commandControls.InvalidVinException;
import com.commands.fileControl.FileRead;
import com.commands.fileControl.FileReadImpl;
import com.database.QueryExecutor;
import com.engineInfo.InvalidEmissionStandardException;
import com.engineInfo.InvalidEngineException;
import com.modelInfo.InvalidModelException;
import com.transmissionInfo.InvalidTransmissionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This is the Main class from which the application is started.
 */
public class MainApp {
    /**
     * Main method of the Application.
     * After starting, write commands on the console,
     * or invoke the commented readQueriesFromFile method to read commands
     * from a file. To exit the console, simply write 'exit'.
     * @param args
     */
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("FactoryConfig.xml");
        QueryExecutor queryExecutor = (QueryExecutor) applicationContext.getBean("queryExecutor");
        queryExecutor.createFactoryTable();

        FactoryInfo factoryInfo = new FactoryInfo("BG", 0);
        Scanner scan = new Scanner(System.in);
        CommandsFactory commandsFactory = new CommandsFactory(factoryInfo, queryExecutor);

        FileRead fileRead = new FileReadImpl();
        //System.out.println(fileRead.readQueriesFromFile("queries.txt", commandsFactory));

        String userInput;
        while ((userInput = scan.nextLine()) != null) {
            try {
                if (userInput.equals("exit")) {
                    break;
                }
                String result = commandsFactory.interpretCommand(userInput);
                System.out.println(result);
            } catch (InvalidModelException e) {
                Logger.getLogger(MainApp.class.getName()).log(Level.WARNING, e.getMessage());
            } catch (InvalidTransmissionException e) {
                Logger.getLogger(MainApp.class.getName()).log(Level.WARNING, e.getMessage());
            } catch (InvalidEngineException e) {
                Logger.getLogger(MainApp.class.getName()).log(Level.WARNING, e.getMessage());
            } catch (InvalidVinException e) {
                Logger.getLogger(MainApp.class.getName()).log(Level.WARNING, e.getMessage());
            } catch (InvalidEmissionStandardException e) {
                Logger.getLogger(MainApp.class.getName()).log(Level.WARNING, e.getMessage());
            } catch (InvalidCommandException e) {
                Logger.getLogger(MainApp.class.getName()).log(Level.WARNING, e.getMessage());
            }
        }
    }
}
