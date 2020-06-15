package com.engineInfo;

/**
 * Class representing Diesel Engine,
 * having displacement and turbo fields.
 */
public class DieselEngine extends Engine {
    private int displacement;
    private boolean turbo;

    public DieselEngine(String engineType, double enginePower, String emissionStandard, int displacement, boolean turbo) {
        super(engineType, enginePower, emissionStandard);
        this.displacement = displacement;
        this.turbo = turbo;
    }

    @Override
    public String toString() {
        if (turbo) {
            return super.toString() + "T-" + super.getEmissionStandard();
        }
        return super.toString() + "-" + super.getEmissionStandard();
    }
}
