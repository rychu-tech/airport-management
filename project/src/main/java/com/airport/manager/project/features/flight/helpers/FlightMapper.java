package com.airport.manager.project.features.flight.helpers;

import com.airport.manager.project.features.flight.models.Flight;
import com.airport.manager.project.features.flight.models.FlightDTO;
import com.airport.manager.project.features.flight.models.FlightHistory;
import com.airport.manager.project.features.flight.models.FlightHistoryDTO;
import com.airport.manager.project.features.flight.repositories.FlightHistoryRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FlightMapper {
    private final ModelMapper modelMapper;
    private final FlightHistoryRepository flightHistoryRepository;
    private final FlightHistoryMapper flightHistoryMapper;
    @Autowired
    public FlightMapper(
            ModelMapper modelMapper,
            FlightHistoryRepository flightHistoryRepository,
            FlightHistoryMapper flightHistoryMapper
    )
    {
        this.modelMapper = modelMapper;
        this.flightHistoryRepository = flightHistoryRepository;
        this.flightHistoryMapper = flightHistoryMapper;
        modelMapper.addMappings(new PropertyMap<Flight, FlightDTO>() {
            @Override
            protected void configure() {
                map().setAirplane_name(source.getAirplane().getName());
                map().setDestination_name(source.getDestination().getName());
                map().setId(source.getId());
                map().setFlight_status_name(String.valueOf(source.getFlightStatus().getName()));
                map().setGate_name(source.getGate().getName());
                map().setUser_id(source.getUser().getId());
                map().setDeparture_time(source.getDepartureTime());
                map().setGate_id(source.getGate().getId());
                map().setDestination_id(source.getDestination().getId());
                map().setAirplane_id(source.getAirplane().getId());
                map().setFlight_status_id(source.getFlightStatus().getId());
            }
        });
    }

    public FlightDTO mapToDTO(Flight flight) {
        FlightDTO flightDTO = modelMapper.map(flight, FlightDTO.class);

        List<FlightHistory> history = flightHistoryRepository.findByFlightId(flight.getId());
        List<FlightHistoryDTO> flightHistoryDTOList = history.stream()
                .map(flightHistoryMapper::mapToDTO)
                .collect(Collectors.toList());

        flightDTO.setHistory(new ArrayList<>(flightHistoryDTOList));
        return flightDTO;
    }

}
