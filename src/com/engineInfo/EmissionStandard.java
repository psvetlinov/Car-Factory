package com.engineInfo;

/**
 * Enum representing the available emission standards in the Factory.
 */

public enum EmissionStandard {
    euro3("euro3"), euro4("euro4"), euro5("euro5"), euro6("euro6");

    private String emissionStandard;

    EmissionStandard(String emissionStandard) {
        this.emissionStandard = emissionStandard;
    }

    public String getEmissionStandard() {
        return emissionStandard;
    }

    /**
     * Method that checks if the provided emission standard is in the enum.
     *
     * @param emissionStandard to be checked
     * @return the correct emission standard if it is present
     * @throws InvalidEmissionStandardException
     */
    public static EmissionStandard checkEmissionStandard(String emissionStandard) throws InvalidEmissionStandardException {
        for (EmissionStandard es : EmissionStandard.values()) {
            if (es.emissionStandard.equals(emissionStandard)) {
                return es;
            }
        }
        throw new InvalidEmissionStandardException("Invalid emission standard");
    }
}
