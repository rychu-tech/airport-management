package com.airport.manager.project.features.flight.services;

import com.airport.manager.project.features.flight.models.Flight;
import com.airport.manager.project.features.flight.models.FlightHistory;
import com.airport.manager.project.features.flight.models.FlightHistoryDTO;
import com.airport.manager.project.features.flight.repositories.FlightHistoryRepository;
import com.airport.manager.project.features.flight.repositories.FlightRepository;
import com.airport.manager.project.features.user.models.User;
import com.airport.manager.project.features.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class FlightHistoryService {
    private final FlightRepository flightRepository;
    private final UserRepository userRepository;
    private final FlightHistoryRepository flightHistoryRepository;
    @Autowired
    public FlightHistoryService(
            FlightRepository flightRepository,
            UserRepository userRepository,
            FlightHistoryRepository flightHistoryRepository
    )
    {
        this.flightRepository = flightRepository;
        this.userRepository = userRepository;
        this.flightHistoryRepository = flightHistoryRepository;
    }

    public FlightHistory addFlightHistory(FlightHistoryDTO flightHistoryDTO) {
        Flight flight = flightRepository.findById(flightHistoryDTO.getFlight_id()).orElse(null);
        User user = userRepository.findById(flightHistoryDTO.getUser_id()).orElse(null);

        FlightHistory flightHistory = new FlightHistory(
                flight,
                user,
                flightHistoryDTO.getComment()
        );

        return flightHistoryRepository.save(flightHistory);

    }
}
