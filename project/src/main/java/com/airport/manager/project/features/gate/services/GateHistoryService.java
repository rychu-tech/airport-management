package com.airport.manager.project.features.gate.services;

import com.airport.manager.project.features.gate.models.GateHistory;
import com.airport.manager.project.features.gate.models.GateHistoryDTO;
import com.airport.manager.project.features.gate.repositories.GateHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GateHistoryService {
    private final GateHistoryRepository gateHistoryRepository;
    @Autowired
    public GateHistoryService(GateHistoryRepository gateHistoryRepository) {
        this.gateHistoryRepository = gateHistoryRepository;
    }

    public GateHistory addGateHistory(GateHistoryDTO gateHistoryDTO) {
        GateHistory gateHistory = new GateHistory(
                gateHistoryDTO.getGate_id(),
                gateHistoryDTO.getUser_id(),
                gateHistoryDTO.getComment()
        );
        return gateHistoryRepository.save(gateHistory);
    }
}
