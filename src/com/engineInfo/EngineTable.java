package com.engineInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class representing a table with all available engines.
 * Provides methods for engine validation by kwh or displacement.
 */
public class EngineTable {
    private List<EngineSpecs> engineSpecsList;

    public EngineTable() {
        initEngines();
        sortByKwh(engineSpecsList);
    }

    /**
     * Method that fills the table of available engines in the Factory.
     * Power is in KWh, Displacement is in Litres.
     */
    public void initEngines() {
        engineSpecsList = new ArrayList<>();
        engineSpecsList.add(new EngineSpecs(1, 55, false, "B"));
        engineSpecsList.add(new EngineSpecs(1, 71.50, true, "B"));

        engineSpecsList.add(new EngineSpecs(2, 147, false, "B"));
        engineSpecsList.add(new EngineSpecs(2, 147, false, "D"));
        engineSpecsList.add(new EngineSpecs(2, 191.10, true, "B"));
        engineSpecsList.add(new EngineSpecs(2, 191.10, true, "D"));

        engineSpecsList.add(new EngineSpecs(3, 245, false, "B"));
        engineSpecsList.add(new EngineSpecs(3, 245, false, "D"));
        engineSpecsList.add(new EngineSpecs(3, 318.50, true, "B"));
        engineSpecsList.add(new EngineSpecs(3, 318.50, true, "D"));

        engineSpecsList.add(new EngineSpecs(4, 253, false, "B"));
        engineSpecsList.add(new EngineSpecs(4, 253, false, "D"));
        engineSpecsList.add(new EngineSpecs(4, 328, true, "B"));
        engineSpecsList.add(new EngineSpecs(4, 328, true, "D"));

        engineSpecsList.add(new EngineSpecs(5, 331, false, "B"));
        engineSpecsList.add(new EngineSpecs(5, 331, false, "D"));
        engineSpecsList.add(new EngineSpecs(5, 430.30, true, "B"));
        engineSpecsList.add(new EngineSpecs(5, 430.30, true, "D"));

        engineSpecsList.add(new EngineSpecs(6, 510, false, "B"));
        engineSpecsList.add(new EngineSpecs(6, 510, false, "D"));
        engineSpecsList.add(new EngineSpecs(6, 663, true, "B"));
        engineSpecsList.add(new EngineSpecs(6, 663, true, "D"));

        engineSpecsList.add(new EngineSpecs(8, 736, false, "B"));
        engineSpecsList.add(new EngineSpecs(8, 958.80, true, "B"));
    }

    /**
     * Method that sorts the list of engines by KWh
     *
     * @param engineSpecsList
     */
    public void sortByKwh(List<EngineSpecs> engineSpecsList) {
        engineSpecsList.sort(Comparator.comparingDouble(EngineSpecs::getPowerInKW));
    }

    /**
     * Method that validates engine only by the provieded engineType
     * as it returns the minimal possible engines for Petrol or Diesel engine.
     *
     * @param engineType is the type of the engine, B-Petrol, D-Diesel
     * @return EngineSpecs object with the returned minimal engine components
     * @throws InvalidEngineException
     */
    public EngineSpecs validateEngineOnlyType(String engineType) throws InvalidEngineException {
        if (engineType.equals("B")) {
            return engineSpecsList.get(0);
        } else if (engineType.equals("D")) {
            for (EngineSpecs engine : engineSpecsList) {
                if (engine.getEngineType().equals("D")) {
                    return engine;
                }
            }
        }
        throw new InvalidEngineException("Invalid engine type");
    }

    /**
     * Method that validates engine by provided horse power
     * by checking in the table and finding the minimal difference between
     * provided hp and engine in the table from the same engineType after binarySearch
     *
     * @param engineType is the type of the engine
     * @param hp         are the desired horse power
     * @return the closest engine available in the table
     */
    public EngineSpecs validateEngineByKwh(String engineType, String hp) {
        double horsePower = Integer.parseInt(hp);
        double kwh = horsePower * 0.74;

        List<EngineSpecs> filteredList = engineSpecsList.stream().filter(engineSpecs -> engineSpecs.getEngineType().equals(engineType)).collect(Collectors.toList());

        EngineSpecs tempEngine = new EngineSpecs(2, kwh, false, engineType);
        int tempEngineIndex = Collections.binarySearch(filteredList, tempEngine, Comparator.comparingDouble(EngineSpecs::getPowerInKW));

        // check if this engine is in the list
        if (tempEngineIndex >= 0) {
            return filteredList.get(tempEngineIndex);
        }
        // check if this engine is "the smallest"
        if (tempEngineIndex == -1) {
            return filteredList.get(0);
        } else {
            return getEngineSpecByIndex(filteredList, tempEngineIndex, kwh);
        }
    }

    /**
     * Method that returns EngineSpecs object with the correct engine
     * as it finds the index of the desired engine with provided hp,
     * then finds the previous and next engine and checks which engine
     * is closer and returns it.
     *
     * @param filteredList    filtered list with the same engine type
     * @param tempEngineIndex is the index of the desired engine from binarySearch method
     * @param kwh             is the hp converted to kwh to search in the list
     * @return EngineSpec object containing correct engine from the EngineTable(engineSpeclist) or filteredList in
     * this method.
     */
    public EngineSpecs getEngineSpecByIndex(List<EngineSpecs> filteredList, int tempEngineIndex, double kwh) {
        int newIndex = (tempEngineIndex + 1) * (-1);
        // check if this engine is "the biggest"
        if (newIndex == filteredList.size()) {
            return filteredList.get(newIndex - 1);
        }
        double lowerBound = filteredList.get(newIndex - 1).getPowerInKW();
        double upperBound = filteredList.get(newIndex).getPowerInKW();

        double upperDifference = upperBound - kwh;
        double lowerDifference = kwh - lowerBound;

        if (upperDifference < lowerDifference) {
            return filteredList.get(newIndex);
        } else {
            return filteredList.get(newIndex - 1);
        }
    }

    /**
     * Method that validate engine by provided displacement as it checks
     * in the EngineTable(engineSpecList) for it and returns the correct engine from it.
     *
     * @param engineType   the type of desired engine
     * @param displacement engine displacement to be searched for
     * @param turbo        the desired engine is with turbo
     * @return EngineSpecs object containing the correct engine from the EngineTable if it is present
     * @throws InvalidEngineException
     */
    public EngineSpecs validateEngineByDisplacement(String engineType, String displacement, boolean turbo) throws InvalidEngineException {
        int displacementCC = Integer.parseInt(displacement);

        for (EngineSpecs e : engineSpecsList) {
            if (engineType.equals(e.getEngineType()) && displacementCC == e.getDisplacement() && turbo == e.isTurbo()) {
                return e;
            }
        }
        throw new InvalidEngineException("Invalid displacement");
    }
}
