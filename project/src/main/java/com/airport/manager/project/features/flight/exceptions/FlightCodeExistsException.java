package com.airport.manager.project.features.flight.exceptions;

public class FlightCodeExistsException extends RuntimeException {
    public FlightCodeExistsException() {
        super("Flight with given code already exists!");
    }
}
