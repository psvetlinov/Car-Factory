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

public class CreateCarCommandTest {
    private FactoryInfo factoryInfo;
    private QueryExecutor queryExecutor;

    @Before
    public void setFactory() {
        factoryInfo = new FactoryInfo("BG", 0);
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("FactoryConfig.xml");
        queryExecutor = (QueryExecutor) applicationContext.getBean("queryExecutor");
        queryExecutor.createFactoryTable();
    }

    //model tests:
    @Test(expected = InvalidModelException.class)
    public void testExecuteCommandInvalidModelNameA() throws InvalidModelException, InvalidTransmissionException, InvalidEngineException, InvalidVinException, InvalidEmissionStandardException, InvalidCommandException {
        CommandsFactory commandsFactory = new CommandsFactory(factoryInfo, queryExecutor);
        Assert.assertEquals("Invalid model name or type",
                commandsFactory.interpretCommand("create car model=A0 engine=D"));
    }

    @Test(expected = InvalidModelException.class)
    public void testExecuteCommandInvalidModelNameDifferenFromA() throws InvalidModelException, InvalidTransmissionException, InvalidEngineException, InvalidVinException, InvalidEmissionStandardException, InvalidCommandException {
        CommandsFactory commandsFactory = new CommandsFactory(factoryInfo, queryExecutor);
        Assert.assertEquals("Invalid model name or type",
                commandsFactory.interpretCommand("create car model=S engine=D"));
    }

    @Test(expected = InvalidModelException.class)
    public void testExecuteCommandInvalidModelType() throws InvalidModelException, InvalidTransmissionException, InvalidEngineException, InvalidVinException, InvalidEmissionStandardException, InvalidCommandException {
        CommandsFactory commandsFactory = new CommandsFactory(factoryInfo, queryExecutor);
        Assert.assertEquals("Invalid model name or type",
                commandsFactory.interpretCommand("create car model=A1-cabrio engine=D"));
    }

    @Test(expected = InvalidModelException.class)
    public void testExecuteCommandInvalidModelNameAndType() throws InvalidModelException, InvalidTransmissionException, InvalidEngineException, InvalidVinException, InvalidEmissionStandardException, InvalidCommandException {
        CommandsFactory commandsFactory = new CommandsFactory(factoryInfo, queryExecutor);
        Assert.assertEquals("Invalid model name or type",
                commandsFactory.interpretCommand("create car model=S7-coupe engine=D"));
    }

    @Test(expected = InvalidModelException.class)
    public void testExecuteCommandInvalidModelNameSuvModel() throws InvalidModelException, InvalidTransmissionException, InvalidEngineException, InvalidVinException, InvalidEmissionStandardException, InvalidCommandException {
        CommandsFactory commandsFactory = new CommandsFactory(factoryInfo, queryExecutor);
        Assert.assertEquals("Invalid model",
                commandsFactory.interpretCommand("create car model=Q7 engine=D"));
    }

    @Test
    public void testExecuteCommandValidAModel() throws InvalidModelException, InvalidTransmissionException, InvalidEngineException, InvalidVinException, InvalidEmissionStandardException, InvalidCommandException {
        CommandsFactory commandsFactory = new CommandsFactory(factoryInfo, queryExecutor);
        queryExecutor.createFactoryTable();
        Assert.assertEquals("Success, created: A5-sedan D-663.0T-euro3 Manual-4 A",
                commandsFactory.interpretCommand("create car model=A5 engine=D-6LT-euro3"));
    }

    @Test
    public void testExecuteCommandValidHatchbackModel() throws InvalidModelException, InvalidTransmissionException, InvalidEngineException, InvalidVinException, InvalidEmissionStandardException, InvalidCommandException {
        CommandsFactory commandsFactory = new CommandsFactory(factoryInfo, queryExecutor);
        queryExecutor.createFactoryTable();
        Assert.assertEquals("Success, created: A5-hatchback D-663.0T-euro3 Manual-4 A",
                commandsFactory.interpretCommand("create car model=A5-hatchback engine=D-6LT-euro3"));
    }

    @Test
    public void testExecuteCommandValidSedanModel() throws InvalidModelException, InvalidTransmissionException, InvalidEngineException, InvalidVinException, InvalidEmissionStandardException, InvalidCommandException {
        CommandsFactory commandsFactory = new CommandsFactory(factoryInfo, queryExecutor);
        queryExecutor.createFactoryTable();
        Assert.assertEquals("Success, created: A4-sedan D-663.0T-euro3 Manual-4 A",
                commandsFactory.interpretCommand("create car model=A4-sedan engine=D-6LT-euro3"));
    }

    @Test
    public void testExecuteCommandValidKombiModel() throws InvalidModelException, InvalidTransmissionException, InvalidEngineException, InvalidVinException, InvalidEmissionStandardException, InvalidCommandException {
        CommandsFactory commandsFactory = new CommandsFactory(factoryInfo, queryExecutor);
        queryExecutor.createFactoryTable();
        Assert.assertEquals("Success, created: A6-kombi D-663.0T-euro3 Manual-4 A",
                commandsFactory.interpretCommand("create car model=A6-kombi engine=D-6LT-euro3"));
    }

    //engine tests:
    @Test
    public void testExecuteCommandOnlyModelAndEngineDiesel() throws InvalidModelException, InvalidTransmissionException, InvalidEngineException, InvalidVinException, InvalidEmissionStandardException, InvalidCommandException {
        CommandsFactory commandsFactory = new CommandsFactory(factoryInfo, queryExecutor);
        Assert.assertEquals("Success, created: A5-sedan D-147.0-euro3 Manual-4 A",
                commandsFactory.interpretCommand("create car model=A5 engine=D"));
    }

    @Test
    public void testExecuteCommandOnlyModelAndEnginePetrol() throws InvalidModelException, InvalidTransmissionException, InvalidEngineException, InvalidVinException, InvalidEmissionStandardException, InvalidCommandException {
        CommandsFactory commandsFactory = new CommandsFactory(factoryInfo, queryExecutor);
        Assert.assertEquals("Success, created: A5-sedan B-55.0-euro3 Manual-4 A",
                commandsFactory.interpretCommand("create car model=A5 engine=B"));
    }

    @Test(expected = InvalidEngineException.class)
    public void testExecuteCommandInvalidEngineType() throws InvalidModelException, InvalidTransmissionException, InvalidEngineException, InvalidVinException, InvalidEmissionStandardException, InvalidCommandException {
        CommandsFactory commandsFactory = new CommandsFactory(factoryInfo, queryExecutor);
        Assert.assertEquals("Invalid engine type",
                commandsFactory.interpretCommand("create car model=A5 engine=M"));
    }

    @Test(expected = InvalidEngineException.class)
    public void testExecuteCommandInvalidMinPetrolEngineByCC() throws InvalidModelException, InvalidTransmissionException, InvalidEngineException, InvalidVinException, InvalidEmissionStandardException, InvalidCommandException {
        CommandsFactory commandsFactory = new CommandsFactory(factoryInfo, queryExecutor);
        Assert.assertEquals("Invalid displacement",
                commandsFactory.interpretCommand("create car model=A5 engine=B-0L"));
    }

    @Test(expected = InvalidEngineException.class)
    public void testExecuteCommandInvalidMaxPetrolEngineByCC() throws InvalidModelException, InvalidTransmissionException, InvalidEngineException, InvalidVinException, InvalidEmissionStandardException, InvalidCommandException {
        CommandsFactory commandsFactory = new CommandsFactory(factoryInfo, queryExecutor);
        Assert.assertEquals("Invalid displacement",
                commandsFactory.interpretCommand("create car model=A5 engine=B-10L"));
    }

    @Test(expected = InvalidEngineException.class)
    public void testExecuteCommandInvalidMinDieselEngineByCC() throws InvalidModelException, InvalidTransmissionException, InvalidEngineException, InvalidVinException, InvalidEmissionStandardException, InvalidCommandException {
        CommandsFactory commandsFactory = new CommandsFactory(factoryInfo, queryExecutor);
        Assert.assertEquals("Invalid displacement",
                commandsFactory.interpretCommand("create car model=A5 engine=D-1L"));
    }

    @Test(expected = InvalidEngineException.class)
    public void testExecuteCommandInvalidMaxDieselEngineByCC() throws InvalidModelException, InvalidTransmissionException, InvalidEngineException, InvalidVinException, InvalidEmissionStandardException, InvalidCommandException {
        CommandsFactory commandsFactory = new CommandsFactory(factoryInfo, queryExecutor);
        Assert.assertEquals("Invalid displacement",
                commandsFactory.interpretCommand("create car model=A5 engine=D-8L"));
    }

    @Test
    public void testExecuteCommandMinPetrolEngineByHp() throws InvalidModelException, InvalidTransmissionException, InvalidEngineException, InvalidVinException, InvalidEmissionStandardException, InvalidCommandException {
        CommandsFactory commandsFactory = new CommandsFactory(factoryInfo, queryExecutor);
        Assert.assertEquals("Success, created: A5-sedan B-55.0-euro3 Manual-4 A",
                commandsFactory.interpretCommand("create car model=A5 engine=B-60hp"));
    }

    @Test
    public void testExecuteCommandMaxSixLPetrolEngineByHp() throws InvalidModelException, InvalidTransmissionException, InvalidEngineException, InvalidVinException, InvalidEmissionStandardException, InvalidCommandException {
        CommandsFactory commandsFactory = new CommandsFactory(factoryInfo, queryExecutor);
        Assert.assertEquals("Success, created: A5-sedan B-663.0T-euro3 Manual-4 A",
                commandsFactory.interpretCommand("create car model=A5 engine=B-900hp"));
    }

    @Test
    public void testExecuteCommandMaxHPPetrolEngineByHp() throws InvalidModelException, InvalidTransmissionException, InvalidEngineException, InvalidVinException, InvalidEmissionStandardException, InvalidCommandException {
        CommandsFactory commandsFactory = new CommandsFactory(factoryInfo, queryExecutor);
        Assert.assertEquals("Success, created: A7-sedan B-958.8T-euro3 Manual-4 A",
                commandsFactory.interpretCommand("create car model=A7 engine=B-1400hp"));
    }

    @Test
    public void testExecuteCommandMidPetrolEngineByHp() throws InvalidModelException, InvalidTransmissionException, InvalidEngineException, InvalidVinException, InvalidEmissionStandardException, InvalidCommandException {
        CommandsFactory commandsFactory = new CommandsFactory(factoryInfo, queryExecutor);
        Assert.assertEquals("Success, created: A3-sedan B-318.5T-euro3 Manual-4 A",
                commandsFactory.interpretCommand("create car model=A3 engine=B-427hp"));
    }

    @Test
    public void testExecuteCommandExactKWDieselEngineByHp() throws InvalidModelException, InvalidTransmissionException, InvalidEngineException, InvalidVinException, InvalidEmissionStandardException, InvalidCommandException {
        CommandsFactory commandsFactory = new CommandsFactory(factoryInfo, queryExecutor);
        Assert.assertEquals("Success, created: A2-sedan D-430.3T-euro3 Manual-4 A",
                commandsFactory.interpretCommand("create car model=A2 engine=D-585hp"));
    }

    @Test
    public void testExecuteCommandExactKWPetrolNoTurboEngineByHp() throws InvalidModelException, InvalidTransmissionException, InvalidEngineException, InvalidVinException, InvalidEmissionStandardException, InvalidCommandException {
        CommandsFactory commandsFactory = new CommandsFactory(factoryInfo, queryExecutor);
        Assert.assertEquals("Success, created: A4-sedan B-245.0-euro3 Manual-4 A",
                commandsFactory.interpretCommand("create car model=A4 engine=B-333hp"));
    }

    @Test(expected = InvalidEmissionStandardException.class)
    public void testExecuteCommandInvalidEmissionsEuro7() throws InvalidModelException, InvalidTransmissionException, InvalidEngineException, InvalidVinException, InvalidEmissionStandardException, InvalidCommandException {
        CommandsFactory commandsFactory = new CommandsFactory(factoryInfo, queryExecutor);
        Assert.assertEquals("Invalid emission standard",
                commandsFactory.interpretCommand("create car model=A4 engine=B-333hp-euro7"));
    }

    @Test(expected = InvalidEmissionStandardException.class)
    public void testExecuteCommandInvalidEmissionsEuro2() throws InvalidModelException, InvalidTransmissionException, InvalidEngineException, InvalidVinException, InvalidEmissionStandardException, InvalidCommandException {
        CommandsFactory commandsFactory = new CommandsFactory(factoryInfo, queryExecutor);
        Assert.assertEquals("Invalid emission standard",
                commandsFactory.interpretCommand("create car model=A4 engine=B-333hp-euro2"));
    }

    @Test
    public void testExecuteCommandValidEmissionsDefault() throws InvalidModelException, InvalidTransmissionException, InvalidEngineException, InvalidVinException, InvalidEmissionStandardException, InvalidCommandException {
        CommandsFactory commandsFactory = new CommandsFactory(factoryInfo, queryExecutor);
        Assert.assertEquals("Success, created: A4-sedan B-245.0-euro3 Manual-4 A",
                commandsFactory.interpretCommand("create car model=A4 engine=B-333hp"));
    }

    //manual transmission tests:
    @Test
    public void testExecuteCommandValidTransmissionDefault() throws InvalidModelException, InvalidTransmissionException, InvalidEngineException, InvalidVinException, InvalidEmissionStandardException, InvalidCommandException {
        CommandsFactory commandsFactory = new CommandsFactory(factoryInfo, queryExecutor);
        Assert.assertEquals("Success, created: A1-sedan B-245.0-euro3 Manual-4 A",
                commandsFactory.interpretCommand("create car model=A1 engine=B-333hp"));
    }

    @Test
    public void testExecuteCommandValidManualTransmissionDefault() throws InvalidModelException, InvalidTransmissionException, InvalidEngineException, InvalidVinException, InvalidEmissionStandardException, InvalidCommandException {
        CommandsFactory commandsFactory = new CommandsFactory(factoryInfo, queryExecutor);
        Assert.assertEquals("Success, created: A1-sedan B-245.0-euro3 Manual-4 A",
                commandsFactory.interpretCommand("create car model=A1 engine=B-333hp transmission=Manual"));
    }

    @Test
    public void testExecuteCommandValidManualFourGearTransmission() throws InvalidModelException, InvalidTransmissionException, InvalidEngineException, InvalidVinException, InvalidEmissionStandardException, InvalidCommandException {
        CommandsFactory commandsFactory = new CommandsFactory(factoryInfo, queryExecutor);
        Assert.assertEquals("Success, created: A1-sedan B-245.0-euro3 Manual-4 A",
                commandsFactory.interpretCommand("create car model=A1 engine=B-333hp transmission=Manual-4"));
    }

    @Test
    public void testExecuteCommandValidManualFiveGearTransmission() throws InvalidModelException, InvalidTransmissionException, InvalidEngineException, InvalidVinException, InvalidEmissionStandardException, InvalidCommandException {
        CommandsFactory commandsFactory = new CommandsFactory(factoryInfo, queryExecutor);
        Assert.assertEquals("Success, created: A2-sedan B-245.0-euro3 Manual-5 A",
                commandsFactory.interpretCommand("create car model=A2 engine=B-333hp transmission=Manual-5"));
    }

    @Test
    public void testExecuteCommandValidManualSixGearTransmission() throws InvalidModelException, InvalidTransmissionException, InvalidEngineException, InvalidVinException, InvalidEmissionStandardException, InvalidCommandException {
        CommandsFactory commandsFactory = new CommandsFactory(factoryInfo, queryExecutor);
        Assert.assertEquals("Success, created: A2-sedan B-245.0-euro3 Manual-6 A",
                commandsFactory.interpretCommand("create car model=A2 engine=B-333hp transmission=Manual-6"));
    }

    @Test(expected = InvalidTransmissionException.class)
    public void testExecuteCommandInvalidManualThreeGearTransmission() throws InvalidModelException, InvalidTransmissionException, InvalidEngineException, InvalidVinException, InvalidEmissionStandardException, InvalidCommandException {
        CommandsFactory commandsFactory = new CommandsFactory(factoryInfo, queryExecutor);
        Assert.assertEquals("Invalid transmission",
                commandsFactory.interpretCommand("create car model=A2 engine=B-333hp transmission=Manual-3"));
    }

    @Test(expected = InvalidTransmissionException.class)
    public void testExecuteCommandInvalidManualSevenGearTransmission() throws InvalidModelException, InvalidTransmissionException, InvalidEngineException, InvalidVinException, InvalidEmissionStandardException, InvalidCommandException {
        CommandsFactory commandsFactory = new CommandsFactory(factoryInfo, queryExecutor);
        Assert.assertEquals("Invalid transmission",
                commandsFactory.interpretCommand("create car model=A2 engine=B-333hp transmission=Manual-7"));
    }

    //automatic transmission tests:
    @Test
    public void testExecuteCommandValidAutoTransmissionDefault() throws InvalidModelException, InvalidTransmissionException, InvalidEngineException, InvalidVinException, InvalidEmissionStandardException, InvalidCommandException {
        CommandsFactory commandsFactory = new CommandsFactory(factoryInfo, queryExecutor);
        Assert.assertEquals("Success, created: A6-sedan B-245.0-euro3 Auto-4 A",
                commandsFactory.interpretCommand("create car model=A6 engine=B-333hp transmission=Auto"));
    }

    @Test
    public void testExecuteCommandValidAutoFourGearTransmission() throws InvalidModelException, InvalidTransmissionException, InvalidEngineException, InvalidVinException, InvalidEmissionStandardException, InvalidCommandException {
        CommandsFactory commandsFactory = new CommandsFactory(factoryInfo, queryExecutor);
        Assert.assertEquals("Success, created: A6-sedan B-245.0-euro3 Auto-4 A",
                commandsFactory.interpretCommand("create car model=A6 engine=B-333hp transmission=Auto-4"));
    }

    @Test
    public void testExecuteCommandValidAutoFiveGearTransmission() throws InvalidModelException, InvalidTransmissionException, InvalidEngineException, InvalidVinException, InvalidEmissionStandardException, InvalidCommandException {
        CommandsFactory commandsFactory = new CommandsFactory(factoryInfo, queryExecutor);
        Assert.assertEquals("Success, created: A6-sedan B-245.0-euro3 Auto-5 A",
                commandsFactory.interpretCommand("create car model=A6 engine=B-333hp transmission=Auto-5"));
    }

    @Test
    public void testExecuteCommandValidAutoSixGearTransmission() throws InvalidModelException, InvalidTransmissionException, InvalidEngineException, InvalidVinException, InvalidEmissionStandardException, InvalidCommandException {
        CommandsFactory commandsFactory = new CommandsFactory(factoryInfo, queryExecutor);
        Assert.assertEquals("Success, created: A6-sedan B-245.0-euro3 Auto-6 A",
                commandsFactory.interpretCommand("create car model=A6 engine=B-333hp transmission=Auto-6"));
    }

    @Test
    public void testExecuteCommandValidAutoEightGearTransmission() throws InvalidModelException, InvalidTransmissionException, InvalidEngineException, InvalidVinException, InvalidEmissionStandardException, InvalidCommandException {
        CommandsFactory commandsFactory = new CommandsFactory(factoryInfo, queryExecutor);
        Assert.assertEquals("Success, created: A6-sedan B-245.0-euro3 Auto-8 A",
                commandsFactory.interpretCommand("create car model=A6 engine=B-333hp transmission=Auto-8"));
    }

    @Test(expected = InvalidTransmissionException.class)
    public void testExecuteCommandInvalidAutoSevenGearTransmission() throws InvalidModelException, InvalidTransmissionException, InvalidEngineException, InvalidVinException, InvalidEmissionStandardException, InvalidCommandException {
        CommandsFactory commandsFactory = new CommandsFactory(factoryInfo, queryExecutor);
        Assert.assertEquals("Invalid transmission",
                commandsFactory.interpretCommand("create car model=A2 engine=B-333hp transmission=Auto-7"));
    }

    @Test(expected = InvalidTransmissionException.class)
    public void testExecuteCommandInvalidAutoTwoGearTransmission() throws InvalidModelException, InvalidTransmissionException, InvalidEngineException, InvalidVinException, InvalidEmissionStandardException, InvalidCommandException {
        CommandsFactory commandsFactory = new CommandsFactory(factoryInfo, queryExecutor);
        Assert.assertEquals("Invalid transmission",
                commandsFactory.interpretCommand("create car model=A2 engine=B-333hp transmission=Auto-2"));
    }

}
