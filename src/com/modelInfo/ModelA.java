package com.modelInfo;

/**
 * Enum representing all the available models from the A-serie
 * as they have model name and body type;
 */
public enum ModelA {
    A1("A1", ""), A2("A2", ""), A3("A3", ""), A4("A4", ""), A5("A5", ""), A6("A6", ""), A7("A7", ""), A8("A8", ""),
    A1h("A1", "hatchback"), A1s("A1", "sedan"), A1k("A1", "kombi"),
    A2h("A2", "hatchback"), A2s("A2", "sedan"), A2k("A2", "kombi"),
    A3h("A3", "hatchback"), A3s("A3", "sedan"), A3k("A3", "kombi"),
    A4h("A4", "hatchback"), A4s("A4", "sedan"), A4k("A4", "kombi"),
    A5h("A5", "hatchback"), A5s("A5", "sedan"), A5k("A5", "kombi"),
    A6h("A6", "hatchback"), A6s("A6", "sedan"), A6k("A6", "kombi"),
    A7h("A7", "hatchback"), A7s("A7", "sedan"), A7k("A7", "kombi"),
    A8h("A8", "hatchback"), A8s("A8", "sedan"), A8k("A8", "kombi");
    private String modelName;
    private String type;

    ModelA(String modelName, String type) {
        this.modelName = modelName;
        this.type = type;
    }

    public String getModelName() {
        return modelName;
    }

    public String getType() {
        if (type.equals("")) {
            return "sedan";
        }
        return type;
    }

    /**
     * Method that checks whether the provided model name and body type from the A-series
     * are present in the enum.
     * @param modelName is the provided model name to be checked
     * @param bodyType is the provided body type to be checked
     * @return the ModelA enum type if it is a correct model from the enum
     * @throws InvalidModelException
     */
    public static ModelA checkModel(String modelName, String bodyType) throws InvalidModelException {
        for (ModelA modelA : ModelA.values()) {
            if (modelA.modelName.equals(modelName) && modelA.type.equals(bodyType)) {
                return modelA;
            }
        }
        throw new InvalidModelException("Invalid model name or type");
    }


}
