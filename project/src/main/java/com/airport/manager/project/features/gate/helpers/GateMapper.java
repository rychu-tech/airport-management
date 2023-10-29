package com.airport.manager.project.features.gate.helpers;

import com.airport.manager.project.features.gate.models.Gate;
import com.airport.manager.project.features.gate.models.GateDTO;
import com.airport.manager.project.features.gate.models.GateHistory;
import com.airport.manager.project.features.gate.models.GateHistoryDTO;
import com.airport.manager.project.features.gate.repositories.GateHistoryRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.hibernate.internal.util.collections.CollectionHelper.map;

@Component
public class GateMapper {
    private final ModelMapper modelMapper;
    private final GateHistoryRepository gateHistoryRepository;
    private final GateHistoryMapper gateHistoryMapper;
    @Autowired
    public GateMapper(
            ModelMapper modelMapper,
            GateHistoryRepository gateHistoryRepository,
            GateHistoryMapper gateHistoryMapper
    )
    {
        this.modelMapper = modelMapper;
        this.gateHistoryRepository = gateHistoryRepository;
        this.gateHistoryMapper = gateHistoryMapper;
    }

    public GateDTO mapToDTO(Gate gate) {
        GateDTO gateDTO = modelMapper.map(gate, GateDTO.class);
        List<GateHistory> history = gateHistoryRepository.findByGateId(gate.getId());
        List<GateHistoryDTO> gateHistoryDTOList = history.stream()
                .map(gateHistoryMapper::mapToDTO)
                .collect(Collectors.toList());

        gateDTO.setHistory(new ArrayList<>(gateHistoryDTOList));
        return gateDTO;
    }
}
