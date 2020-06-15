package com.engineInfo;

/**
 * Class representing Petrol Engine,
 * having displacement and turbo fields.
 */
public class PetrolEngine extends Engine {
    private int displacement;
    private boolean turbo;

    public PetrolEngine(String engineType, double enginePower, String emissionStandard) {
        super(engineType, enginePower, emissionStandard);
    }

    public PetrolEngine(String engineType, int displacement, double enginePower, boolean turbo, String emissionStandard) {
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
