package com.validator;

import com.engineInfo.EngineSpecs;
import com.engineInfo.InvalidEmissionStandardException;
import com.engineInfo.InvalidEngineException;
import com.modelInfo.InvalidModelException;
import com.transmissionInfo.InvalidTransmissionException;

/**
 * Interface providing methods for validation of different
 * vehicle components.
 */
public interface Validate {
    /**
     * Method that accepts the model info from the create command input as an
     * argument and then validates the data.
     *
     * @param modelInfo is the model info from the create command input
     * @param type      is the vehicle type (car or suv)
     * @return String of the validated model name + body type(if it is present).
     * @throws InvalidModelException
     */
    String validateModel(String modelInfo, String type) throws InvalidModelException;

    /**
     * Method that accepts the engine info from the create command input as an
     * argument and then validates the data.
     *
     * @param engineInfo is the engine info, obtained from the create command input
     * @return EngineSpecs object with validated engine components from the EngineTable.
     * @throws InvalidEngineException
     */
    EngineSpecs validateEngine(String engineInfo) throws InvalidEngineException;

    /**
     * Method that accepts the transmission info from the create command input as an
     * argument and then validates the data.
     *
     * @param transmissionInfo is the transmission info, obtained from the create command input
     * @return String of the validated transmission type and gears.
     * @throws InvalidTransmissionException
     */
    String validateTransmission(String transmissionInfo) throws InvalidTransmissionException;

    /**
     * Method that accepts the emission standard info from the create command input as an
     * argument and then validates the data.
     *
     * @param emissionStandard is the emission standard info, obtained from the create command input
     * @return String of the validated emission standard
     * @throws InvalidEmissionStandardException
     */
    String validateEmissionStandard(String emissionStandard) throws InvalidEmissionStandardException;

}
