package com.modelInfo;

/**
 * Class representing vehicle Model which has vehicle model name
 * and vehicle body type
 */
public class Model {
    String modelName;
    String bodyType;

    public Model(String modelName) {
        this.modelName = modelName;
    }

    public Model(String modelName, String bodyType) {
        this.modelName = modelName;
        this.bodyType = bodyType;
    }

    public String getModelName() {
        return modelName;
    }

    public String getBodyType() {
        return bodyType;
    }

    @Override
    public String toString() {
        if (bodyType == null) {
            return modelName;
        }
        return modelName + "-" + bodyType;
    }
}
