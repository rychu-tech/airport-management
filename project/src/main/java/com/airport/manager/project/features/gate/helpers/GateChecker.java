package com.airport.manager.project.features.gate.helpers;

import com.airport.manager.project.features.gate.exceptions.GateNameExistsException;
import com.airport.manager.project.features.gate.exceptions.GateNotActiveException;
import com.airport.manager.project.features.gate.models.Gate;
import com.airport.manager.project.features.gate.repositories.GateRepository;
import org.springframework.stereotype.Component;

@Component
public class GateChecker {
    private final GateRepository gateRepository;
    public GateChecker(GateRepository gateRepository) {
        this.gateRepository = gateRepository;
    }

    public void checkGateName(String name) throws GateNameExistsException {
        Gate gate = gateRepository.findByName(name);
        if (gate != null) {
            throw new GateNameExistsException();
        }
    }

    public void checkGateActive(Long gateId) throws GateNotActiveException {
        Gate gate = gateRepository.findById(gateId).orElse(null);
        if (gate != null && !gate.getActive()) {
            throw new GateNotActiveException();
        }
    }

}
