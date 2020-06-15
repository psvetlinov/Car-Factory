package com.commands.createCommand;

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

public class CreateSuvCommandTest {
    private FactoryInfo factoryInfo;
    private QueryExecutor queryExecutor;

    @Before
    public void setFactory() {
        factoryInfo = new FactoryInfo("BG", 0);
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("FactoryConfig.xml");
        queryExecutor = (QueryExecutor) applicationContext.getBean("queryExecutor");
        queryExecutor.createFactoryTable();
    }

    @Test
    public void testExecuteCommandValidQModel() throws InvalidModelException, InvalidTransmissionException, InvalidEngineException, InvalidVinException, InvalidEmissionStandardException, InvalidCommandException {
        CommandsFactory commandsFactory = new CommandsFactory(factoryInfo, queryExecutor);
        queryExecutor.createFactoryTable();
        Assert.assertEquals("Success, created: Q1 D-663.0T-euro3 Manual-4 A",
                commandsFactory.interpretCommand("create suv model=Q1 engine=D-6LT-euro3"));
    }

    @Test(expected = InvalidModelException.class)
    public void testExecuteCommandInvalidModelAType() throws InvalidModelException, InvalidTransmissionException, InvalidEngineException, InvalidVinException, InvalidEmissionStandardException, InvalidCommandException {
        CommandsFactory commandsFactory = new CommandsFactory(factoryInfo, queryExecutor);
        Assert.assertEquals("Invalid model",
                commandsFactory.interpretCommand("create suv model=A2 engine=D"));
    }

    @Test(expected = InvalidModelException.class)
    public void testExecuteCommandInvalidQModelAType() throws InvalidModelException, InvalidTransmissionException, InvalidEngineException, InvalidVinException, InvalidEmissionStandardException, InvalidCommandException {
        CommandsFactory commandsFactory = new CommandsFactory(factoryInfo, queryExecutor);
        Assert.assertEquals("Invalid model",
                commandsFactory.interpretCommand("create suv model=Q12 engine=D"));
    }

    @Test(expected = InvalidModelException.class)
    public void testExecuteCommandInvalidModelNameAndType() throws InvalidModelException, InvalidTransmissionException, InvalidEngineException, InvalidVinException, InvalidEmissionStandardException, InvalidCommandException {
        CommandsFactory commandsFactory = new CommandsFactory(factoryInfo, queryExecutor);
        Assert.assertEquals("Invalid model name or type",
                commandsFactory.interpretCommand("create suv model=Q9-coupe engine=D"));
    }

    @Test(expected = InvalidModelException.class)
    public void testExecuteCommandInvalidNonExistBodyType() throws InvalidModelException, InvalidTransmissionException, InvalidEngineException, InvalidVinException, InvalidEmissionStandardException, InvalidCommandException {
        CommandsFactory commandsFactory = new CommandsFactory(factoryInfo, queryExecutor);
        Assert.assertEquals("Invalid model name or type",
                commandsFactory.interpretCommand("create suv model=Q1-coupe engine=D"));
    }

    @Test(expected = InvalidModelException.class)
    public void testExecuteCommandInvalidBodyType() throws InvalidModelException, InvalidTransmissionException, InvalidEngineException, InvalidVinException, InvalidEmissionStandardException, InvalidCommandException {
        CommandsFactory commandsFactory = new CommandsFactory(factoryInfo, queryExecutor);
        Assert.assertEquals("SUV do not have bodyType",
                commandsFactory.interpretCommand("create suv model=Q1-hatchback engine=D"));
    }

    @Test(expected = InvalidModelException.class)
    public void testExecuteCommandInvalidModelNameDifferenFromQ() throws InvalidModelException, InvalidTransmissionException, InvalidEngineException, InvalidVinException, InvalidEmissionStandardException, InvalidCommandException {
        CommandsFactory commandsFactory = new CommandsFactory(factoryInfo, queryExecutor);
        Assert.assertEquals("Invalid model name or type",
                commandsFactory.interpretCommand("create suv model=B engine=D"));
    }

    @Test(expected = InvalidEngineException.class)
    public void testExecuteCommandInvalidEngineOnly() throws InvalidModelException, InvalidTransmissionException, InvalidEngineException, InvalidVinException, InvalidEmissionStandardException, InvalidCommandException {
        CommandsFactory commandsFactory = new CommandsFactory(factoryInfo, queryExecutor);
        Assert.assertEquals("Invalid engine type",
                commandsFactory.interpretCommand("create suv model=Q1 engine=W"));
    }


    //engine tests:
    @Test
    public void testExecuteCommandOnlyModelAndEngineDiesel() throws InvalidModelException, InvalidTransmissionException, InvalidEngineException, InvalidVinException, InvalidEmissionStandardException, InvalidCommandException {
        CommandsFactory commandsFactory = new CommandsFactory(factoryInfo, queryExecutor);
        Assert.assertEquals("Success, created: Q1 D-147.0-euro3 Manual-4 A",
                commandsFactory.interpretCommand("create suv model=Q1 engine=D"));
    }

    @Test
    public void testExecuteCommandOnlyModelAndEnginePetrol() throws InvalidModelException, InvalidTransmissionException, InvalidEngineException, InvalidVinException, InvalidEmissionStandardException, InvalidCommandException {
        CommandsFactory commandsFactory = new CommandsFactory(factoryInfo, queryExecutor);
        Assert.assertEquals("Success, created: Q2 B-55.0-euro3 Manual-4 A",
                commandsFactory.interpretCommand("create suv model=Q2 engine=B"));
    }

    @Test(expected = InvalidEngineException.class)
    public void testExecuteCommandInvalidEngineType() throws InvalidModelException, InvalidTransmissionException, InvalidEngineException, InvalidVinException, InvalidEmissionStandardException, InvalidCommandException {
        CommandsFactory commandsFactory = new CommandsFactory(factoryInfo, queryExecutor);
        Assert.assertEquals("Invalid engine type",
                commandsFactory.interpretCommand("create suv model=Q3 engine=M"));
    }

    @Test(expected = InvalidEngineException.class)
    public void testExecuteCommandInvalidMinPetrolEngineByCC() throws InvalidModelException, InvalidTransmissionException, InvalidEngineException, InvalidVinException, InvalidEmissionStandardException, InvalidCommandException {
        CommandsFactory commandsFactory = new CommandsFactory(factoryInfo, queryExecutor);
        Assert.assertEquals("Invalid displacement",
                commandsFactory.interpretCommand("create suv model=Q3 engine=B-0L"));
    }

    @Test(expected = InvalidEngineException.class)
    public void testExecuteCommandInvalidMaxPetrolEngineByCC() throws InvalidModelException, InvalidTransmissionException, InvalidEngineException, InvalidVinException, InvalidEmissionStandardException, InvalidCommandException {
        CommandsFactory commandsFactory = new CommandsFactory(factoryInfo, queryExecutor);
        Assert.assertEquals("Invalid displacement",
                commandsFactory.interpretCommand("create suv model=Q4 engine=B-10L"));
    }

    @Test(expected = InvalidEngineException.class)
    public void testExecuteCommandInvalidMinDieselEngineByCC() throws InvalidModelException, InvalidTransmissionException, InvalidEngineException, InvalidVinException, InvalidEmissionStandardException, InvalidCommandException {
        CommandsFactory commandsFactory = new CommandsFactory(factoryInfo, queryExecutor);
        Assert.assertEquals("Invalid displacement",
                commandsFactory.interpretCommand("create suv model=Q5 engine=D-1L"));
    }

    @Test(expected = InvalidEngineException.class)
    public void testExecuteCommandInvalidMaxDieselEngineByCC() throws InvalidModelException, InvalidTransmissionException, InvalidEngineException, InvalidVinException, InvalidEmissionStandardException, InvalidCommandException {
        CommandsFactory commandsFactory = new CommandsFactory(factoryInfo, queryExecutor);
        Assert.assertEquals("Invalid displacement",
                commandsFactory.interpretCommand("create suv model=Q6 engine=D-8L"));
    }

    @Test
    public void testExecuteCommandMinPetrolEngineByHp() throws InvalidModelException, InvalidTransmissionException, InvalidEngineException, InvalidVinException, InvalidEmissionStandardException, InvalidCommandException {
        CommandsFactory commandsFactory = new CommandsFactory(factoryInfo, queryExecutor);
        Assert.assertEquals("Success, created: Q7 B-55.0-euro3 Manual-4 A",
                commandsFactory.interpretCommand("create suv model=Q7 engine=B-60hp"));
    }

    @Test
    public void testExecuteCommandMaxSixLPetrolEngineByHp() throws InvalidModelException, InvalidTransmissionException, InvalidEngineException, InvalidVinException, InvalidEmissionStandardException, InvalidCommandException {
        CommandsFactory commandsFactory = new CommandsFactory(factoryInfo, queryExecutor);
        Assert.assertEquals("Success, created: Q8 B-663.0T-euro3 Manual-4 A",
                commandsFactory.interpretCommand("create suv model=Q8 engine=B-900hp"));
    }

    @Test
    public void testExecuteCommandMaxHPPetrolEngineByHp() throws InvalidModelException, InvalidTransmissionException, InvalidEngineException, InvalidVinException, InvalidEmissionStandardException, InvalidCommandException {
        CommandsFactory commandsFactory = new CommandsFactory(factoryInfo, queryExecutor);
        Assert.assertEquals("Success, created: Q1 B-958.8T-euro3 Manual-4 A",
                commandsFactory.interpretCommand("create suv model=Q1 engine=B-1400hp"));
    }

    @Test
    public void testExecuteCommandMidPetrolEngineByHp() throws InvalidModelException, InvalidTransmissionException, InvalidEngineException, InvalidVinException, InvalidEmissionStandardException, InvalidCommandException {
        CommandsFactory commandsFactory = new CommandsFactory(factoryInfo, queryExecutor);
        Assert.assertEquals("Success, created: Q2 B-318.5T-euro3 Manual-4 A",
                commandsFactory.interpretCommand("create suv model=Q2 engine=B-427hp"));
    }

    @Test
    public void testExecuteCommandExactKWDieselEngineByHp() throws InvalidModelException, InvalidTransmissionException, InvalidEngineException, InvalidVinException, InvalidEmissionStandardException, InvalidCommandException {
        CommandsFactory commandsFactory = new CommandsFactory(factoryInfo, queryExecutor);
        Assert.assertEquals("Success, created: Q3 D-430.3T-euro3 Manual-4 A",
                commandsFactory.interpretCommand("create suv model=Q3 engine=D-585hp"));
    }

    @Test
    public void testExecuteCommandExactKWPetrolNoTurboEngineByHp() throws InvalidModelException, InvalidTransmissionException, InvalidEngineException, InvalidVinException, InvalidEmissionStandardException, InvalidCommandException {
        CommandsFactory commandsFactory = new CommandsFactory(factoryInfo, queryExecutor);
        Assert.assertEquals("Success, created: Q4 B-245.0-euro3 Manual-4 A",
                commandsFactory.interpretCommand("create suv model=Q4 engine=B-333hp"));
    }

    @Test(expected = InvalidEmissionStandardException.class)
    public void testExecuteCommandInvalidEmissionsEuro7() throws InvalidModelException, InvalidTransmissionException, InvalidEngineException, InvalidVinException, InvalidEmissionStandardException, InvalidCommandException {
        CommandsFactory commandsFactory = new CommandsFactory(factoryInfo, queryExecutor);
        Assert.assertEquals("Invalid emission standard",
                commandsFactory.interpretCommand("create suv model=Q5 engine=B-333hp-euro7"));
    }

    @Test(expected = InvalidEmissionStandardException.class)
    public void testExecuteCommandInvalidEmissionsEuro2() throws InvalidModelException, InvalidTransmissionException, InvalidEngineException, InvalidVinException, InvalidEmissionStandardException, InvalidCommandException {
        CommandsFactory commandsFactory = new CommandsFactory(factoryInfo, queryExecutor);
        Assert.assertEquals("Invalid emission standard",
                commandsFactory.interpretCommand("create suv model=Q6 engine=B-333hp-euro2"));
    }

    @Test
    public void testExecuteCommandValidEmissionsDefault() throws InvalidModelException, InvalidTransmissionException, InvalidEngineException, InvalidVinException, InvalidEmissionStandardException, InvalidCommandException {
        CommandsFactory commandsFactory = new CommandsFactory(factoryInfo, queryExecutor);
        Assert.assertEquals("Success, created: Q7 B-245.0-euro3 Manual-4 A",
                commandsFactory.interpretCommand("create suv model=Q7 engine=B-333hp"));
    }

}
