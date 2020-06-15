package com.engineInfo;

/**
 * Class that represents the structure of engine in the EngineTable
 * with all available engines in the Factory.
 */
public class EngineSpecs {
    private int displacement;
    private double powerInKW;
    private boolean turbo;
    private String engineType;

    public EngineSpecs(int displacement, double powerInKW, boolean turbo, String engineType) {
        this.displacement = displacement;
        this.powerInKW = powerInKW;
        this.turbo = turbo;
        this.engineType = engineType;
    }

    public double getPowerInKW() {
        return powerInKW;
    }

    public int getDisplacement() {
        return displacement;
    }

    public boolean isTurbo() {
        return turbo;
    }

    public String getEngineType() {
        return engineType;
    }
}

