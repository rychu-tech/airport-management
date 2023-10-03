package com.airport.manager.project.features.destination.exceptions;

public class DestinationNameExistsException extends RuntimeException{
    public DestinationNameExistsException() {
        super("Destination with such name exists!");
    }
}
