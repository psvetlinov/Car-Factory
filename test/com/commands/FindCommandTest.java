package com.commands;

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
 * Class testing the find command.
 */
public class FindCommandTest {
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
    public void testExecuteCommandFind() throws InvalidEngineException, InvalidVinException, InvalidTransmissionException, InvalidCommandException, InvalidEmissionStandardException, InvalidModelException {
        CommandsFactory commandsFactory = new CommandsFactory(factoryInfo, queryExecutor);
        queryExecutor.createFactoryTable();
        String result=queryExecutor.findAll("euro5");
        Assert.assertEquals(result,commandsFactory.interpretCommand("find euro5"));
    }
}
