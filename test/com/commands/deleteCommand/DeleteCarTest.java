package com.commands.deleteCommand;

import com.FactoryInfo;
import com.commands.commandControls.CommandsFactory;
import com.commands.commandControls.InvalidCommandException;
import com.commands.commandControls.InvalidVinException;
import com.database.QueryExecutor;
import com.engineInfo.InvalidEmissionStandardException;
import com.engineInfo.InvalidEngineException;
import com.modelInfo.InvalidModelException;
import com.transmissionInfo.InvalidTransmissionException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Class testing the delete car command with various cases.
 */
public class DeleteCarTest {

    private FactoryInfo factoryInfo;
    private QueryExecutor queryExecutor;

    @Before
    public void setFactory() {
        factoryInfo = new FactoryInfo("BG", 0);
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("FactoryConfig.xml");
        queryExecutor = (QueryExecutor) applicationContext.getBean("queryExecutor");
        queryExecutor.createFactoryTable();
        queryExecutor.dropTable();
    }

    @Test
    public void testExecuteCommandValidDeleteByPrint() throws InvalidEngineException, InvalidVinException, InvalidTransmissionException, InvalidCommandException, InvalidEmissionStandardException, InvalidModelException {
        CommandsFactory commandsFactory = new CommandsFactory(factoryInfo, queryExecutor);
        queryExecutor.createFactoryTable();
        Assert.assertEquals("Success, created: A5-sedan D-663.0T-euro3 Manual-4 A",
                commandsFactory.interpretCommand("create car model=A5 engine=D-6LT-euro3"));

        String vin = queryExecutor.getAllVins().get(0);
        commandsFactory.interpretCommand("delete car " + vin);
        Assert.assertEquals(commandsFactory.interpretCommand("print all"),
                commandsFactory.interpretCommand("print vin " + vin));
    }

    @Test(expected = InvalidVinException.class)
    public void testExecuteCommandInvalidDeleteADeletedCar() throws InvalidEngineException, InvalidVinException, InvalidTransmissionException, InvalidCommandException, InvalidEmissionStandardException, InvalidModelException {
        CommandsFactory commandsFactory = new CommandsFactory(factoryInfo, queryExecutor);
        queryExecutor.createFactoryTable();
        Assert.assertEquals("Success, created: A1-hatchback D-663.0T-euro3 Manual-4 A",
                commandsFactory.interpretCommand("create car model=A1-hatchback engine=D-6LT-euro3"));

        String vin = queryExecutor.getAllVins().get(0);
        commandsFactory.interpretCommand("delete car " + vin);
        Assert.assertEquals("Invalid VIN or already deleted vehicle",
                commandsFactory.interpretCommand("delete car " + vin));
    }

    @Test
    public void testExecuteCommandValidDeleteByStatus() throws InvalidEngineException, InvalidVinException, InvalidTransmissionException, InvalidCommandException, InvalidEmissionStandardException, InvalidModelException {
        CommandsFactory commandsFactory = new CommandsFactory(factoryInfo, queryExecutor);
        queryExecutor.createFactoryTable();
        Assert.assertEquals("Success, created: A4-sedan D-663.0T-euro3 Manual-4 A",
                commandsFactory.interpretCommand("create car model=A4 engine=D-6LT-euro3"));

        String vin = queryExecutor.getAllVins().get(0);
        commandsFactory.interpretCommand("delete car " + vin);
        String status = queryExecutor.getStatus(vin);
        Assert.assertEquals("D", status);
    }

    @Test(expected = InvalidVinException.class)
    public void testExecuteCommandInvalidVinDelete() throws InvalidEngineException, InvalidVinException, InvalidTransmissionException, InvalidCommandException, InvalidEmissionStandardException, InvalidModelException {
        CommandsFactory commandsFactory = new CommandsFactory(factoryInfo, queryExecutor);
        queryExecutor.createFactoryTable();
        Assert.assertEquals("Success, created: A4-sedan D-663.0T-euro3 Manual-4 A",
                commandsFactory.interpretCommand("create car model=A4 engine=D-6LT-euro3"));
        String vin = queryExecutor.getAllVins().get(0);
        Assert.assertEquals("Invalid VIN or already deleted vehicle", commandsFactory.interpretCommand("delete car " + vin + "1"));
    }
}
