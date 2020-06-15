package com.validator;

import com.engineInfo.*;
import com.modelInfo.InvalidModelException;
import com.modelInfo.ModelA;
import com.modelInfo.ModelQ;
import com.transmissionInfo.Automatic;
import com.transmissionInfo.InvalidTransmissionException;
import com.transmissionInfo.Manual;

/**
 * Class implementing methods from Validate interface.
 * Used in construction of a vehicle in create commands.
 */
public class Validator implements Validate {
    private ModelA modelA;
    private ModelQ modelQ;
    private EngineTable engineTable;
    private Manual manual;
    private Automatic automatic;

    @Override
    public String validateModel(String modelInfo, String type) throws InvalidModelException {
        String[] modelBuffer = modelInfo.split("-");
        String modelName = modelBuffer[0].substring(modelBuffer[0].length() - 2);
        String bodyType = "";

        if (modelBuffer.length > 1 && modelName.charAt(0) == 'Q') {
            throw new InvalidModelException("SUV do not have bodyType");
        } else {
            if (modelBuffer.length > 1) {
                bodyType = modelBuffer[1];
            }
            if (modelName.charAt(0) == 'A' && type.equals("car")) {
                ModelA mA = ModelA.checkModel(modelName, bodyType);
                return mA.getModelName() + " " + mA.getType();
            } else if (modelName.charAt(0) == 'Q' && type.equals("suv")) {
                ModelQ mQ = ModelQ.checkModel(modelName);
                return mQ.getModelName();
            }
        }
        throw new InvalidModelException("Invalid model");
    }

    @Override
    public EngineSpecs validateEngine(String engineInfo) throws InvalidEngineException {
        String[] engineBuffer = engineInfo.split("-");
        EngineTable engineTable = new EngineTable();
        EngineSpecs engineToBeBuilt;

        String engineType = engineBuffer[0].substring(engineBuffer[0].length() - 1);
        boolean turbo = false;
        if (engineBuffer.length < 2) {
            engineToBeBuilt = engineTable.validateEngineOnlyType(engineType);
        } else {
            if (engineBuffer[1].endsWith("p")) {
                String getHp = engineBuffer[1].substring(0, engineBuffer[1].length() - 2);
                engineToBeBuilt = engineTable.validateEngineByKwh(engineType, getHp);
            } else {
                if (engineBuffer[1].endsWith("T")) {
                    turbo = true;
                }
                int idxOfLitres = engineBuffer[1].indexOf("L");
                String getCC = engineBuffer[1].substring(0, idxOfLitres);
                engineToBeBuilt = engineTable.validateEngineByDisplacement(engineType, getCC, turbo);
            }
        }
        return engineToBeBuilt;
    }

    @Override
    public String validateTransmission(String transmissionInfo) throws InvalidTransmissionException {
        String[] transmissionBuffer = transmissionInfo.split("-");
        int index = transmissionBuffer[0].indexOf("=");
        String transmissionType = transmissionBuffer[0].substring(index + 1);
        String gears = "";
        if (transmissionBuffer.length > 1) {
            gears = transmissionBuffer[1];
        }
        if (transmissionType.equals("Manual")) {
            Manual man = Manual.checkTransmission(transmissionType, gears);
            return man.getType() + " " + man.getGears();
        } else {
            Automatic auto = Automatic.checkTransmission(transmissionType, gears);
            return auto.getType() + " " + auto.getGears();
        }
    }

    @Override
    public String validateEmissionStandard(String emissionStandard) throws InvalidEmissionStandardException {
        EmissionStandard emissions = EmissionStandard.checkEmissionStandard(emissionStandard);
        return emissions.getEmissionStandard();
    }
}
