package com.airport.manager.project.features.gate.services;

import com.airport.manager.project.features.airplane.models.Airplane;
import com.airport.manager.project.features.gate.helpers.GateChecker;
import com.airport.manager.project.features.gate.helpers.GateMapper;
import com.airport.manager.project.features.gate.models.Gate;
import com.airport.manager.project.features.gate.models.GateDTO;
import com.airport.manager.project.features.gate.models.GateHistory;
import com.airport.manager.project.features.gate.models.GateHistoryDTO;
import com.airport.manager.project.features.gate.repositories.GateRepository;
import com.airport.manager.project.features.user.models.User;
import com.airport.manager.project.features.user.models.UserDetailsPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class GateService {
    private final GateRepository gateRepository;
    private final GateChecker gateChecker;
    private final GateHistoryService gateHistoryService;
    private final GateMapper gateMapper;


    @Autowired
    public GateService(
            GateRepository gateRepository,
            GateChecker gateChecker,
            GateHistoryService gateHistoryService,
            GateMapper gateMapper
    )
    {
        this.gateRepository = gateRepository;
        this.gateChecker = gateChecker;
        this.gateHistoryService = gateHistoryService;
        this.gateMapper = gateMapper;
    }

    public Gate addGate(GateDTO gateDTO, User user) {
        String gateName = gateDTO.getName();
        gateChecker.checkGateName(gateName);
        Gate gate = new Gate(gateName);
        gateRepository.save(gate);

        GateHistoryDTO gateHistoryDTO = new GateHistoryDTO(
                gate.getId(),
                user.getId(),
                "Created"
        );
        gateHistoryService.addGateHistory(gateHistoryDTO);

        return gate;
    }

    public GateDTO getGateInDTOFormat(Long gateId) {
        Gate gate = gateRepository.findById(gateId).orElse(null);
        return gateMapper.mapToDTO(gate);
    }

    public List<GateDTO> getAllGatesInDTOFormat() {
        List<Gate> gates = gateRepository.findAll();
        return gates.stream()
                .map(gateMapper::mapToDTO)
                .collect(Collectors.toList());
    }

    public Gate changeGateActiveStatus(Long gateId, Boolean active, String comment, User user) {
        Gate gate = gateRepository.findById(gateId).orElse(null);

        if (gate != null) {
            gate.setActive(active);
            GateHistoryDTO gateHistoryDTO = new GateHistoryDTO(
                    gateId,
                    user.getId(),
                    comment
            );
            gateHistoryService.addGateHistory(gateHistoryDTO);

            gateRepository.save(gate);
        }

        return gate;
    }
}
