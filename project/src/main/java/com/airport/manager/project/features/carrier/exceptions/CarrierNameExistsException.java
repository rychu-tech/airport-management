package com.airport.manager.project.features.carrier.exceptions;

public class CarrierNameExistsException extends RuntimeException {
    public CarrierNameExistsException() {
        super("Carrier with such name exists!");
    }

    public CarrierNameExistsException(String message) {
        super(message);
    }
}
