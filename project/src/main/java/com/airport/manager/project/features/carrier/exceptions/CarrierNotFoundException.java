package com.airport.manager.project.features.carrier.exceptions;

public class CarrierNotFoundException extends RuntimeException {
    public CarrierNotFoundException() {
        super("The carrier with given Id was not found!");
    }

    public CarrierNotFoundException(String message) {
        super(message);
    }
}