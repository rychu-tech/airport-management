package com.airport.manager.project.features.destination.exceptions;

public class DestinationNotActiveException extends RuntimeException {
    public DestinationNotActiveException() {
        super("Destination with this Id is not active!");
    }
}
