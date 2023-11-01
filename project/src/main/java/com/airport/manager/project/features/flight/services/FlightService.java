package com.airport.manager.project.features.flight.services;

import com.airport.manager.project.features.airplane.helpers.AirplaneChecker;
import com.airport.manager.project.features.airplane.models.Airplane;
import com.airport.manager.project.features.airplane.repositories.AirplaneRepository;
import com.airport.manager.project.features.destination.helpers.DestinationChecker;
import com.airport.manager.project.features.destination.models.Destination;
import com.airport.manager.project.features.destination.repositories.DestinationRepository;
import com.airport.manager.project.features.flight.exceptions.FlightWrongDataException;
import com.airport.manager.project.features.flight.helpers.FlightChecker;
import com.airport.manager.project.features.flight.helpers.FlightMapper;
import com.airport.manager.project.features.flight.models.Flight;
import com.airport.manager.project.features.flight.models.FlightDTO;
import com.airport.manager.project.features.flight.models.FlightHistoryDTO;
import com.airport.manager.project.features.flight.models.FlightStatus;
import com.airport.manager.project.features.flight.repositories.FlightHistoryRepository;
import com.airport.manager.project.features.flight.repositories.FlightRepository;
import com.airport.manager.project.features.flight.repositories.FlightStatusRepository;
import com.airport.manager.project.features.gate.helpers.GateChecker;
import com.airport.manager.project.features.gate.models.Gate;
import com.airport.manager.project.features.gate.repositories.GateRepository;
import com.airport.manager.project.features.user.models.User;
import com.airport.manager.project.features.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class FlightService {
    private final FlightRepository flightRepository;
    private final FlightStatusRepository flightStatusRepository;
    private final FlightHistoryRepository flightHistoryRepository;
    private final GateChecker gateChecker;
    private final UserRepository userRepository;
    private final GateRepository gateRepository;
    private final DestinationRepository destinationRepository;
    private final DestinationChecker destinationChecker;
    private final FlightChecker flightChecker;
    private final AirplaneChecker airplaneChecker;
    private final AirplaneRepository airplaneRepository;
    protected final FlightMapper flightMapper;
    private final FlightHistoryService flightHistoryService;
    @Autowired
    public FlightService(
            FlightRepository flightRepository,
            FlightStatusRepository flightStatusRepository,
            FlightHistoryRepository flightHistoryRepository,
            GateChecker gateChecker,
            UserRepository userRepository,
            GateRepository gateRepository,
            DestinationRepository destinationRepository,
            DestinationChecker destinationChecker,
            FlightChecker flightChecker,
            AirplaneChecker airplaneChecker,
            AirplaneRepository airplaneRepository,
            FlightMapper flightMapper,
            FlightHistoryService flightHistoryService
    )
    {
        this.flightRepository = flightRepository;
        this.flightStatusRepository = flightStatusRepository;
        this.flightHistoryRepository = flightHistoryRepository;
        this.gateChecker = gateChecker;
        this.userRepository = userRepository;
        this.gateRepository = gateRepository;
        this.destinationRepository = destinationRepository;
        this.destinationChecker = destinationChecker;
        this.flightChecker = flightChecker;
        this.airplaneChecker = airplaneChecker;
        this.airplaneRepository = airplaneRepository;
        this.flightMapper = flightMapper;
        this.flightHistoryService = flightHistoryService;
    }

    public Flight addFlight(FlightDTO flightDTO) throws FlightWrongDataException {
        gateChecker.checkGateActive(flightDTO.getGate_id());
        destinationChecker.checkDestinationActive(flightDTO.getDestination_id());
        flightChecker.checkFlightCode(flightDTO.getCode());
        airplaneChecker.checkAirplaneActive(flightDTO.getAirplane_id());

        User user = userRepository.findById(flightDTO.getUser_id()).orElse(null);
        Gate gate = gateRepository.findById(flightDTO.getGate_id()).orElse(null);
        Destination destination = destinationRepository.findById(flightDTO.getDestination_id()).orElse(null);
        FlightStatus flightStatus = flightStatusRepository.findById(flightDTO.getFlight_status_id()).orElse(null);
        Airplane airplane = airplaneRepository.findById(flightDTO.getAirplane_id()).orElse(null);

        Flight flight = new Flight();
        flight.setUser(user);
        flight.setDepartureTime(flightDTO.getDeparture_time());
        flight.setGate(gate);
        flight.setDestination(destination);
        flight.setFlightStatus(flightStatus);
        flight.setCode(flightDTO.getCode());
        flight.setAirplane(airplane);

        if (flight.getFlightStatus() == null ||
                flight.getCode() == null ||
                flight.getAirplane() == null ||
                flight.getDestination() == null ||
                flight.getGate() == null
        )
        {
            throw new FlightWrongDataException();
        }
        flightRepository.save(flight);

        FlightHistoryDTO flightHistoryDTO = new FlightHistoryDTO(
                flight.getId(),
                user.getId(),
                String.valueOf(flightStatus.getName())
        );

        flightHistoryService.addFlightHistory(flightHistoryDTO);

        return flight;
    }

    public List<FlightDTO> getAllFlightsInDTOFormat() {
        List<Flight> flights = flightRepository.findAll();
        return flights.stream()
                .map(flightMapper::mapToDTO)
                .collect(Collectors.toList());
    }

    public FlightDTO getFlightInDTOFormat(Long flightId) {
        Flight flight = flightRepository.findById(flightId).orElse(null);
        return flightMapper.mapToDTO(flight);
    }

}
