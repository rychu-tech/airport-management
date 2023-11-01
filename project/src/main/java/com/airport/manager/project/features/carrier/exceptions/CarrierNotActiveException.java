package com.airport.manager.project.features.carrier.exceptions;

public class CarrierNotActiveException extends RuntimeException {
    public CarrierNotActiveException() {
        super("Carrier with given Id is not active!");
    }
}
