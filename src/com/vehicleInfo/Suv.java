package com.vehicleInfo;

import com.FactoryInfo;
import com.engineInfo.Engine;
import com.modelInfo.Model;
import com.transmissionInfo.Transmission;

public class Suv extends Vehicle {
    
    public Suv(Model model, Engine engine, Transmission transmission, FactoryInfo factory, String type, String status) {
        super(model, engine, transmission, factory, type, status);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
