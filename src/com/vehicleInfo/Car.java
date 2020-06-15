package com.vehicleInfo;

import com.FactoryInfo;
import com.engineInfo.Engine;
import com.modelInfo.Model;
import com.transmissionInfo.Transmission;

public class Car extends Vehicle {

    public Car(Model model, Engine engine, Transmission transmission, FactoryInfo factory, String type, String status) {
        super(model, engine, transmission, factory, type, status);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
