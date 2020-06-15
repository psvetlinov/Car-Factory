package com.commands.fileControl;

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

public class FileReadImplTest {
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
    public void testreadQueriesFromFile() throws InvalidEngineException, InvalidVinException, InvalidTransmissionException, InvalidCommandException, InvalidEmissionStandardException, InvalidModelException {
        CommandsFactory commandsFactory = new CommandsFactory(factoryInfo, queryExecutor);
        FileReadImpl reader=new FileReadImpl();
        Assert.assertEquals(reader.readQueriesFromFile("queries.txt",commandsFactory),
                commandsFactory.interpretCommand("create car model=A1 engine=D"));

    }
}
