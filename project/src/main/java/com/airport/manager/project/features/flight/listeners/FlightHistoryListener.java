package com.airport.manager.project.features.flight.listeners;

import com.airport.manager.project.features.flight.models.FlightHistory;
import jakarta.persistence.PrePersist;

import java.util.Date;

public class FlightHistoryListener {
    @PrePersist
    public void prePersist(FlightHistory flightHistory) {
        Date now = new Date();
        flightHistory.setCreatedAt(now);
    }
}
