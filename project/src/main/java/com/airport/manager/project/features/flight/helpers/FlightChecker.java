package com.airport.manager.project.features.flight.helpers;

import com.airport.manager.project.features.flight.exceptions.FlightCodeExistsException;
import com.airport.manager.project.features.flight.models.Flight;
import com.airport.manager.project.features.flight.repositories.FlightRepository;
import org.springframework.stereotype.Component;

@Component
public class FlightChecker {
    private final FlightRepository flightRepository;
    public FlightChecker(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public void checkFlightCode(String code) throws FlightCodeExistsException {
        Flight flight = flightRepository.findByCode(code);
        if (flight != null) {
            throw new FlightCodeExistsException();
        }

    }
}
