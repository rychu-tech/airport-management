package com.airport.manager.project.features.flight.listeners;

import com.airport.manager.project.features.flight.models.Flight;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.util.Date;

public class FlightListener {
    @PrePersist
    public void prePersist(Flight flight) {
        Date now = new Date();
        flight.setCreatedAt(now);
        flight.setUpdatedAt(now);
    }

    @PreUpdate
    public void preUpdate(Flight flight) {
        flight.setUpdatedAt(new Date());
    }
}
