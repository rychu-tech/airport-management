package com.airport.manager.project.features.airplane.exceptions;

public class AirplaneStatusNotFoundException extends RuntimeException{
    public AirplaneStatusNotFoundException() {
        super("The airplane status with given Id was not found!");
    }
}
