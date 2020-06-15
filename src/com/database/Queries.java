package com.database;

import com.vehicleInfo.Vehicle;

import java.util.List;

/**
 * Interface providing list of methods
 * that execute SQL queries to the Factory DB.
 */
public interface Queries {
    /**
     * Method that creates a Factory table to store all the vehicles.
     */
    void createFactoryTable();

    /**
     * Method that inserts a Vehicle into the Factory DB.
     *
     * @param vehicle is the vehicle to be added to the Factory
     * @return Success if the query is executed correctly.
     */
    String insert(Vehicle vehicle);

    /**
     * Method that gets a list of all VINs from the FactoryDB
     *
     * @return list of VINs.
     */
    List<String> getAllVins();

    /**
     * Method that gets a list of all VINs of created vehicles from the FactoryDB
     *
     * @return list of assembled vehicles.
     */
    List<String> getCreatedVehiclesVins();

    /**
     * Method that deletes a vehicle by its VIN from the DB"
     *
     * @param VIN of the vehicle to be deleted
     * @return Success if the query is executed correctly.
     */
    String delete(String VIN);

    /**
     * Method that prints the info of a vehicle by provided VIN.
     *
     * @param VIN of the vehicle to be shown info
     * @return full info of the vehicle.
     */
    String print(String VIN);

    /**
     * Method that prints the info of all vehicles in the FactoryDB.
     *
     * @return full info of all vehicles.
     */
    String printAll();

    /**
     * Method that return the status(Assembled (A), Dissassembled (D)) of the vehicle with the provided VIN.
     *
     * @param VIN of the vehicle to be shown Status
     * @return A if it is Assembled or D if it is Dissassembled
     */
    String getStatus(String VIN);

    /**
     * Method that find all vehicles with that have the provided emission standard
     *
     * @param emissions is the emission standard to be looked for in the Factory DB
     * @return list of info of all vehicles that have this emmision standard
     */
    String findAll(String emissions);

    /**
     * Method that update emission column of the vehicle with new emission standard in the FactoryDB
     *
     * @param VIN                 of the vehicle, which emissionStandard column will be updated
     * @param newEmissionStandard the new value of the emissionStandard column
     * @return the result of executing the update query
     */
    String updateEmissions(String VIN, String newEmissionStandard);

    /**
     * Method that update the emmisionStandard in Engine column of the vehicle by its VIN in the Factory DB
     *
     * @param VIN               of the vehicle, which Engine info will be updated with new emission standard
     * @param newEngineEmission the new value of the emissionStandard
     * @return result of executing the update query
     */
    String updateEngineEmissions(String VIN, String newEngineEmission);

    /**
     * Method that updates the Engine column by adding turbo if it is not present.
     *
     * @param VIN      of the car on which turbo could be mounted if it is not present
     * @param newTurbo String containing the info of the new Engine if turbo is mounted
     *                 or the same if turbo cannot be mounted.
     * @return the result of executing the update query
     */
    String updateTurbo(String VIN, String newTurbo);

    /**
     * Method that updates Transmission column ot a vehicle in the FactoryDB.
     *
     * @param VIN             of the vehicle, which transmission will be updated
     * @param newTransmission the new value of the transmission
     * @return the result of executing the update query
     */
    String updateTransmission(String VIN, String newTransmission);

    String dropTable();

}
