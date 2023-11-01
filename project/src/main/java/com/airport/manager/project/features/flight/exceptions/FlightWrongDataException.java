package com.airport.manager.project.features.flight.exceptions;

public class FlightWrongDataException extends RuntimeException {
    public FlightWrongDataException() {
        super("There is error in provided data or something is missing!");
    }
}
