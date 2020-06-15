package com.commands.createCommand;

import com.validator.Validator;
import com.engineInfo.*;
import com.modelInfo.InvalidModelException;
import com.modelInfo.Model;
import com.transmissionInfo.InvalidTransmissionException;
import com.transmissionInfo.Transmission;

/**
 * Class implementing Buildable interface.
 * Contains implementation of all Buildable methods.
 */
public class VehicleBuilder implements Buildable {

    /**
     * Method that obtains model info from the create command
     *
     * @param modelInfo is the model provided by the create command
     * @param validator is used to validate the model components
     * @param type      is the type of the vehicle (car, suv)
     * @return validated Model object
     * @throws InvalidModelException
     */
    @Override
    public Model buildModel(String modelInfo, Validator validator, String type) throws InvalidModelException {
        String validatedModel = validator.validateModel(modelInfo, type);
        String[] modelBuffer = validatedModel.split(" ");
        Model model;
        if (modelBuffer.length < 2) {
            model = new Model(modelBuffer[0]);
        } else {
            model = new Model(modelBuffer[0], modelBuffer[1]);
        }
        return model;
    }

    /**
     * Method that obtains engine info from the create command
     *
     * @param engineInfo contains the engine parameters provided by the create command
     * @param validator  is used to validate the engine components
     * @return validated Engine object as it can be PetrolEngine or DieselEngine
     * @throws InvalidEmissionStandardException
     * @throws InvalidEngineException
     */
    @Override
    public Engine buildEngine(String engineInfo, Validator validator) throws InvalidEmissionStandardException, InvalidEngineException {
        String[] engineBuffer = engineInfo.split("-");
        String emissions = "euro3";
        if (engineBuffer.length > 2) {
            emissions = validator.validateEmissionStandard(engineBuffer[2]);
        }
        Engine engine;
        EngineSpecs validatedEngine = validator.validateEngine(engineInfo);
        if (validatedEngine.getEngineType().equals("B")) {
            engine = new PetrolEngine("B", validatedEngine.getDisplacement(),
                    validatedEngine.getPowerInKW(), validatedEngine.isTurbo(), emissions);
        } else {
            engine = new DieselEngine("D", validatedEngine.getPowerInKW(), emissions,
                    validatedEngine.getDisplacement(), validatedEngine.isTurbo());
        }
        return engine;
    }

    /**
     * Method that obtains transmission info from the create command
     *
     * @param transmissionInfo contains the transmission parameters provided by the create command
     * @param validator        is used to validate transmission components
     * @return validated Transmission object
     * @throws InvalidTransmissionException
     */
    @Override
    public Transmission buildTransmission(String transmissionInfo, Validator validator) throws InvalidTransmissionException {
        int gears = 4;
        String validatedTransmission = validator.validateTransmission(transmissionInfo);
        String[] transmissionBuffer = validatedTransmission.split(" ");
        if (transmissionBuffer.length > 1) {
            gears = Integer.parseInt(transmissionBuffer[1]);
        }
        String transmissionType = transmissionBuffer[0];
        return new Transmission(transmissionType, gears);
    }
}
