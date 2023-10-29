package com.airport.manager.project.features.gate.listeners;

import com.airport.manager.project.features.gate.models.Gate;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.util.Date;

public class GateListener {
    @PrePersist
    public void prePersist(Gate gate) {
        Date now = new Date();
        gate.setCreatedAt(now);
        gate.setUpdatedAt(now);
    }

    @PreUpdate
    public void preUpdate(Gate gate) {
        gate.setUpdatedAt(new Date());
    }
}
