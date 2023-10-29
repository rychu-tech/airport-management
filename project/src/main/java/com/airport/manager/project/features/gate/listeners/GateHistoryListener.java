package com.airport.manager.project.features.gate.listeners;

import com.airport.manager.project.features.gate.models.GateHistory;
import jakarta.persistence.PrePersist;

import java.util.Date;

public class GateHistoryListener {
    @PrePersist
    public void prePersist(GateHistory gateHistory) {
        Date now = new Date();
        gateHistory.setCreatedAt(now);
    }
}
