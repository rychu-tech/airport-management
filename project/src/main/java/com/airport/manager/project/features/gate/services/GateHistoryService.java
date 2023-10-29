package com.airport.manager.project.features.gate.services;

import com.airport.manager.project.features.gate.models.Gate;
import com.airport.manager.project.features.gate.models.GateHistory;
import com.airport.manager.project.features.gate.models.GateHistoryDTO;
import com.airport.manager.project.features.gate.repositories.GateHistoryRepository;
import com.airport.manager.project.features.gate.repositories.GateRepository;
import com.airport.manager.project.features.user.models.User;
import com.airport.manager.project.features.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GateHistoryService {
    private final GateHistoryRepository gateHistoryRepository;
    private final GateRepository gateRepository;
    private final UserRepository userRepository;
    @Autowired
    public GateHistoryService(
            GateHistoryRepository gateHistoryRepository,
            GateRepository gateRepository,
            UserRepository userRepository
    )
    {
        this.gateHistoryRepository = gateHistoryRepository;
        this.gateRepository = gateRepository;
        this.userRepository = userRepository;
    }

    public GateHistory addGateHistory(GateHistoryDTO gateHistoryDTO) {
        Gate gate = gateRepository.findById(gateHistoryDTO.getGate_id()).orElse(null);
        User user = userRepository.findById(gateHistoryDTO.getUser_id()).orElse(null);

        GateHistory gateHistory = new GateHistory(
                gate,
                user,
                gateHistoryDTO.getComment()
        );
        return gateHistoryRepository.save(gateHistory);
    }
}
