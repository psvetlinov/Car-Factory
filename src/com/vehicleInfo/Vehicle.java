package com.vehicleInfo;

import com.FactoryInfo;
import com.engineInfo.Engine;
import com.modelInfo.Model;
import com.transmissionInfo.Transmission;

import java.util.Random;

/**
 * Abstract class representing a real world Vehicle.
 * Fields are used to assemble new Car or new SUV vehicles,
 * as FactoryInfo instance keeps the information where the vehicle have been
 * assembled.
 */
public abstract class Vehicle {
    private String VIN;
    private Model model;
    private Engine engine;
    private Transmission transmission;
    private FactoryInfo factory;
    private String type;
    private String status;

    public Vehicle(Model model, Engine engine, Transmission transmission, FactoryInfo factory, String type, String status) {
        this.VIN = generateVIN(factory);
        this.model = model;
        this.engine = engine;
        this.transmission = transmission;
        this.factory = factory;
        this.type = type;
        this.status = status;
    }

    /**
     * Method that generates unique VIN for every assembled vehicle
     * with provided country and factory number.
     * @param factory shows the country and factory number where
     *               the vehicle has been assembled
     * @return 17-digit string representing the unique VIN of the vehicle.
     */
    private static String generateVIN(FactoryInfo factory) {
        String VIN = "";
        VIN += factory.getCountry();
        VIN += factory.getFactoryNumber();
        char[] symbols = "ABCDEFGHJKLMNPRSTUVWXYZ0123456789".toCharArray();
        Random random = new Random();
        for (int i = 0; i < 14; i++) {
            int randomIndex = random.nextInt(symbols.length);
            VIN += symbols[randomIndex];
        }
        return VIN;
    }

    /*private String generateUniqueVIN(FactoryInfo factory){
        do{
            String currentVIN=generateVIN(factory);
        }while (checkVINInDataBase(currentVIN));
        return currentVIN;
    }*/

    public String getVIN() {
        return VIN;
    }

    public Model getModel() {
        return model;
    }

    public Engine getEngine() {
        return engine;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public String getType() {
        return type;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return //VIN + " " +
                model.toString() +
                        " " + engine.toString() +
                        " " + transmission.toString() + " " + status;
    }
}
