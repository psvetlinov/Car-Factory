package com.commands.createCommand;

import com.validator.Validator;
import com.engineInfo.Engine;
import com.engineInfo.InvalidEmissionStandardException;
import com.engineInfo.InvalidEngineException;
import com.modelInfo.InvalidModelException;
import com.modelInfo.Model;
import com.transmissionInfo.InvalidTransmissionException;
import com.transmissionInfo.Transmission;

/**
 * Interface providing functionality of building a vehicle
 * by its components.
 */
public interface Buildable {
    /**
     * @param modelInfo is the model provided by the create command
     * @param validator is used to validate the model components
     * @param type      is the type of the vehicle (car, suv)
     * @return the final validated Model object
     * @throws InvalidModelException
     */
    Model buildModel(String modelInfo, Validator validator, String type) throws InvalidModelException;

    /**
     * @param engineInfo contains the engine parameters provided by the create command
     * @param validator  is used to validate the engine components
     * @return the final validated Engine object
     * @throws InvalidEmissionStandardException
     * @throws InvalidEngineException
     */
    Engine buildEngine(String engineInfo, Validator validator) throws InvalidEmissionStandardException, InvalidEngineException;

    /**
     * @param transmissionInfo contains the transmission parameters provided by the create command
     * @param validator        is used to validate transmission components
     * @return the final validated Transmission object
     * @throws InvalidTransmissionException
     */
    Transmission buildTransmission(String transmissionInfo, Validator validator) throws InvalidTransmissionException;

}
