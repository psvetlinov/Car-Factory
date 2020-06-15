package com.engineInfo;

/**
 * Abstract class representing the Engine components.
 */
public abstract class Engine {
    private String engineType;
    private double enginePower;
    private String emissionStandard;

    public Engine(String engineType, double enginePower, String emissionStandard) {
        this.engineType = engineType;
        this.enginePower = enginePower;
        this.emissionStandard = emissionStandard;
    }

    public String getEmissionStandard() {
        return emissionStandard;
    }

    @Override
    public String toString() {
        return engineType + "-" + enginePower;
    }
}
