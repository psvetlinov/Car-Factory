package com.transmissionInfo;

/**
 * Enum representing the available vehicle automatic transmissions in the Factory.
 */
public enum Automatic {
    autoDef("Auto", ""),
    auto4("Auto", "4"),
    auto5("Auto", "5"),
    auto6("Auto", "6"),
    auto8("Auto", "8"),;

    private String type;
    private String gears;

    Automatic(String type, String gears) {
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
     * @return an Automatic enum type if the provided transmission is correct and from the enum.
     * @throws InvalidTransmissionException
     */
    public static Automatic checkTransmission(String transmission, String gears) throws InvalidTransmissionException {
        for (Automatic auto : Automatic.values()) {
            if (auto.type.equals(transmission) && auto.gears.equals(gears)) {
                return auto;
            }
        }
        throw new InvalidTransmissionException("Invalid transmission");
    }
}
