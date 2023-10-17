package com.airport.manager.project.features.gate.exceptions;

public class GateNameExistsException extends RuntimeException {
    public GateNameExistsException() {
        super("Gate with such name exists!");
    }
}
