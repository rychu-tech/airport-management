package com.airport.manager.project.features.flight.services;

import com.airport.manager.project.features.flight.models.Flight;
import com.airport.manager.project.features.flight.models.FlightDTO;
import com.airport.manager.project.features.flight.repositories.FlightHistoryRepository;
import com.airport.manager.project.features.flight.repositories.FlightRepository;
import com.airport.manager.project.features.flight.repositories.FlightStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FlightService {
    private final FlightRepository flightRepository;
    private final FlightStatusRepository flightStatusRepository;
    private final FlightHistoryRepository flightHistoryRepository;
    @Autowired
    public FlightService(
            FlightRepository flightRepository,
            FlightStatusRepository flightStatusRepository,
            FlightHistoryRepository flightHistoryRepository
    )
    {
        this.flightRepository = flightRepository;
        this.flightStatusRepository = flightStatusRepository;
        this.flightHistoryRepository = flightHistoryRepository;
    }

    public Flight addFlight(FlightDTO flightDTO) {


        Flight flight = new Flight();


        return flight;
    }

}
