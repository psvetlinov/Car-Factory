package com.transmissionInfo;

/**
 * Enum representing the available vehicle manual transmissions in the Factory.
 */
public enum Manual {
    manDef("Manual", ""),
    man4("Manual", "4"),
    man5("Manual", "5"),
    man6("Manual", "6"),;

    private String type;
    private String gears;

    Manual(String type, String gears) {
        this.type = type;
        this.gears = gears;
    }

    public String getType() {
        return type;
    }

    public String getGears() {
        if (gears.equals("")) {
            return "";
        }
        return gears;
    }

    /**
     * Method that checks if the provided transmission type and gears are present in the enum.
     *
     * @param transmission is the provided transmission type to be checked
     * @param gears        is the provided gear number to be checked
     * @return an Manual enum type if the provided transmission is correct and from the enum.
     * @throws InvalidTransmissionException
     */
    public static Manual checkTransmission(String transmission, String gears) throws InvalidTransmissionException {
        for (Manual man : Manual.values()) {
            if (man.type.equals(transmission) && man.gears.equals(gears)) {
                return man;
            }
        }
        throw new InvalidTransmissionException("Invalid transmission");
    }
}
