package com.transmissionInfo;

/**
 * Class representing vehicle transmission,
 * as it can be Manual or Automatic.
 */
public class Transmission {
    private String transmissionType;
    private int gears;

    public Transmission(String transmissionType, int gears) {
        this.transmissionType = transmissionType;
        this.gears = gears;
    }

    @Override
    public String toString() {
        return transmissionType + "-" + gears;
    }
}
