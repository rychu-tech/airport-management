package com.airport.manager.project.features.gate.services;

import com.airport.manager.project.features.gate.helpers.GateChecker;
import com.airport.manager.project.features.gate.models.Gate;
import com.airport.manager.project.features.gate.models.GateDTO;
import com.airport.manager.project.features.gate.models.GateHistoryDTO;
import com.airport.manager.project.features.gate.repositories.GateRepository;
import com.airport.manager.project.features.user.models.User;
import com.airport.manager.project.features.user.models.UserDetailsPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional

public class GateService {
    private final GateRepository gateRepository;
    private final GateChecker gateChecker;
    private final GateHistoryService gateHistoryService;


    @Autowired
    public GateService(
            GateRepository gateRepository,
            GateChecker gateChecker,
            GateHistoryService gateHistoryService
    )
    {
        this.gateRepository = gateRepository;
        this.gateChecker = gateChecker;
        this.gateHistoryService = gateHistoryService;
    }

    public Gate addGate(GateDTO gateDTO, User user) {
        String gateName = gateDTO.getName();
        gateChecker.checkGateName(gateName);
        Gate gate = new Gate(gateName);
        gateRepository.save(gate);

        GateHistoryDTO gateHistoryDTO = new GateHistoryDTO(
                gate,
                user,
                "Created"
        );
        gateHistoryService.addGateHistory(gateHistoryDTO);

        return gate;
    }
}
