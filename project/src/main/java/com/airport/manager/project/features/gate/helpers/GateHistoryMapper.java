package com.airport.manager.project.features.gate.helpers;

import com.airport.manager.project.features.gate.models.GateHistory;
import com.airport.manager.project.features.gate.models.GateHistoryDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GateHistoryMapper {
    private final ModelMapper modelMapper;
    @Autowired
    public GateHistoryMapper(
            ModelMapper modelMapper
    )
    {
        this.modelMapper = modelMapper;
        modelMapper.addMappings(new PropertyMap<GateHistory, GateHistoryDTO>() {
            @Override
            protected void configure() {
                map().setUser_name(source.getUser().getName());
                map().setGate_id(source.getGate().getId());
                map().setUser_id(source.getUser().getId());
                map().setCreated_at(source.getCreatedAt());
            }
        });
    }

    public GateHistoryDTO mapToDTO(GateHistory gateHistory) {
        return modelMapper.map(gateHistory, GateHistoryDTO.class);
    }
}
