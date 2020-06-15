package com.commands.createCommand;

import com.FactoryInfo;
import com.commands.commandControls.Command;
import com.commands.commandControls.InvalidVinException;
import com.validator.Validator;
import com.database.QueryExecutor;
import com.engineInfo.*;
import com.modelInfo.InvalidModelException;
import com.modelInfo.Model;
import com.transmissionInfo.InvalidTransmissionException;
import com.transmissionInfo.Transmission;
import com.vehicleInfo.Car;
import com.vehicleInfo.Vehicle;

/**
 * Class implementing Command interface.
 * Contains of FactoryInfo and QueryExecutor private fields.
 * Providing functionality of creating a car and its storage into Factory data base.
 */
public class CreateCarCommand implements Command {
    private FactoryInfo factoryInfo;
    private QueryExecutor queryExecutor;

    public CreateCarCommand(FactoryInfo factoryInfo, QueryExecutor queryExecutor) {
        this.factoryInfo = factoryInfo;
        this.queryExecutor = queryExecutor;
    }

    /**
     * Using Vehicle builder, executeCommand builds the car from its
     * already built components-Model,Engine,Transmission.
     * Car is constructed by the components and type of vehicle is set to car
     * and status of the car is set to A(assembled).
     *
     * @param input contains the command input info after the command name, ex. model=A5 engine=D..
     * @return the info of the created car and result of inserting it into the DB
     * @throws InvalidModelException
     * @throws InvalidEngineException
     * @throws InvalidTransmissionException
     * @throws InvalidEmissionStandardException
     */
    @Override
    public String executeCommand(String input) throws InvalidModelException, InvalidEngineException, InvalidTransmissionException, InvalidEmissionStandardException {
        VehicleBuilder vehicleBuilder = new VehicleBuilder();
        String[] currentInput = input.split(" ");
        String modelInfo = currentInput[0];
        String engineInfo = currentInput[1];

        Validator validator = new Validator();
        Model model = vehicleBuilder.buildModel(modelInfo, validator, "car");
        Engine engine = vehicleBuilder.buildEngine(engineInfo, validator);
        String transmissionType = "Manual";
        int gears = 4;
        Transmission transmission;
        if (currentInput.length > 2) {
            String transmissionInfo = currentInput[2];
            transmission = vehicleBuilder.buildTransmission(transmissionInfo, validator);
        } else {
            transmission = new Transmission(transmissionType, gears);
        }

        Vehicle car = new Car(model, engine, transmission, factoryInfo, "car", "A");

        return queryExecutor.insert(car) + ", created: " + car.toString();
    }
}
