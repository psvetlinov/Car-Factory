package com;

/**
 * Class representing the information for the country
 * and the factory number of a Factory that assembles and disassembles vehicles.
 */
public class FactoryInfo {
    private String country;
    private int factoryNumber;

    public FactoryInfo() {
    }

    public FactoryInfo(String country, int factoryNumber) {
        this.country = country;
        this.factoryNumber = factoryNumber;
    }

    public String getCountry() {
        return country;
    }

    public int getFactoryNumber() {
        return factoryNumber;
    }
}
