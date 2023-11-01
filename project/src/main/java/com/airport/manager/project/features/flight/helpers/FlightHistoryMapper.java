package com.airport.manager.project.features.flight.helpers;

import com.airport.manager.project.features.flight.models.FlightHistory;
import com.airport.manager.project.features.flight.models.FlightHistoryDTO;
import com.airport.manager.project.features.gate.models.GateHistory;
import com.airport.manager.project.features.gate.models.GateHistoryDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FlightHistoryMapper {
    private final ModelMapper modelMapper;
    @Autowired
    public FlightHistoryMapper(
            ModelMapper modelMapper
    )
    {
        this.modelMapper = modelMapper;
        modelMapper.addMappings(new PropertyMap<FlightHistory, FlightHistoryDTO>() {
            @Override
            protected void configure() {
                map().setUser_name(source.getUser().getName());
                map().setFlight_id(source.getFlight().getId());
                map().setUser_id(source.getUser().getId());
                map().setCreated_at(source.getCreatedAt());
            }
        });
    }
    public FlightHistoryDTO mapToDTO(FlightHistory flightHistory) {
        return modelMapper.map(flightHistory, FlightHistoryDTO.class);
    }
}
