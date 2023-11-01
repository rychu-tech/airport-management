package com.airport.manager.project.features.gate.exceptions;

public class GateNotActiveException extends RuntimeException {
    public GateNotActiveException() {
        super("Gate with given Id is not active!");
    }
}
