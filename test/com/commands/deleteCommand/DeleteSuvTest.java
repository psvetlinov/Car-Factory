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
public class DeleteSuvTest {
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
        Assert.assertEquals("Success, created: Q3 D-430.3T-euro3 Manual-4 A",
                commandsFactory.interpretCommand("create suv model=Q3 engine=D-585hp"));

        String vin = queryExecutor.getAllVins().get(0);
        commandsFactory.interpretCommand("delete suv " + vin);
        Assert.assertEquals(commandsFactory.interpretCommand("print all"),
                commandsFactory.interpretCommand("print vin " + vin));
    }

    @Test(expected = InvalidVinException.class)
    public void testExecuteCommandInvalidDeleteADeletedSuv() throws InvalidEngineException, InvalidVinException, InvalidTransmissionException, InvalidCommandException, InvalidEmissionStandardException, InvalidModelException {
        CommandsFactory commandsFactory = new CommandsFactory(factoryInfo, queryExecutor);
        queryExecutor.createFactoryTable();
        Assert.assertEquals("Success, created: Q4 B-245.0-euro3 Manual-4 A",
                commandsFactory.interpretCommand("create suv model=Q4 engine=B-333hp"));

        String vin = queryExecutor.getAllVins().get(0);
        commandsFactory.interpretCommand("delete car " + vin);
        Assert.assertEquals("Invalid VIN or already deleted vehicle",
                commandsFactory.interpretCommand("delete suv " + vin));
    }

    @Test
    public void testExecuteCommandValidDeleteByStatus() throws InvalidEngineException, InvalidVinException, InvalidTransmissionException, InvalidCommandException, InvalidEmissionStandardException, InvalidModelException {
        CommandsFactory commandsFactory = new CommandsFactory(factoryInfo, queryExecutor);
        queryExecutor.createFactoryTable();
        Assert.assertEquals("Success, created: Q3 D-663.0T-euro3 Manual-4 A",
                commandsFactory.interpretCommand("create suv model=Q3 engine=D-6LT-euro3"));

        String vin = queryExecutor.getAllVins().get(0);
        commandsFactory.interpretCommand("delete suv " + vin);
        String status = queryExecutor.getStatus(vin);
        Assert.assertEquals("D", status);
    }

    @Test(expected = InvalidVinException.class)
    public void testExecuteCommandInvalidVinDelete() throws InvalidEngineException, InvalidVinException, InvalidTransmissionException, InvalidCommandException, InvalidEmissionStandardException, InvalidModelException {
        CommandsFactory commandsFactory = new CommandsFactory(factoryInfo, queryExecutor);
        queryExecutor.createFactoryTable();
        Assert.assertEquals("Success, created: Q2 B-318.5T-euro3 Manual-4 A",
                commandsFactory.interpretCommand("create suv model=Q2 engine=B-427hp"));
        String vin = queryExecutor.getAllVins().get(0);
        Assert.assertEquals("Invalid VIN or already deleted vehicle", commandsFactory.interpretCommand("delete suv " + vin + "112"));
    }
}
