package com.airport.manager.project.features.destination.exceptions;


public class DestinationNotFoundException extends RuntimeException {
    public DestinationNotFoundException() {
        super("Destination with given Id was not found!");
    }
}
