package com.airport.manager.project.features.airplane.exceptions;

public class AirplaneSeatsNumberInvalidException extends RuntimeException {
    public AirplaneSeatsNumberInvalidException() {
        super("Seats number value must be positive!");
    }
}
