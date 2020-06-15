package com.modelInfo;
/**
 * Enum representing all the available models from the Q-serie
 * as they have only model name.
 */
public enum ModelQ {
    Q1("Q1"), Q2("Q2"), Q3("Q3"), Q4("Q4"), Q5("Q5"), Q6("Q6"), Q7("Q7"), Q8("Q8");

    private String modelName;

    ModelQ(String modelName) {
        this.modelName = modelName;
    }

    public String getModelName() {
        return modelName;
    }

    /**
     * Method that checks whether the provided model name from the Q-series
     * is present in the enum.
     * @param modelName is the provided model name to be checked
     * @return the ModelQ enum type if it is a correct model from the enum
     * @throws InvalidModelException
     */
    public static ModelQ checkModel(String modelName) throws InvalidModelException {
        for (ModelQ modelQ : ModelQ.values()) {
            if (modelQ.modelName.equals(modelName)) {
                return modelQ;
            }
        }
        throw new InvalidModelException("Invalid model name");
    }
}
