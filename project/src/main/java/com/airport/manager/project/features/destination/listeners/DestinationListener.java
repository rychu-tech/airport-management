package com.airport.manager.project.features.destination.listeners;

import com.airport.manager.project.features.destination.models.Destination;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.util.Date;

public class DestinationListener {

    @PrePersist
    public void prePersist(Destination destination) {
        Date now = new Date();
        destination.setCreatedAt(now);
        destination.setUpdatedAt(now);
    }

    @PreUpdate
    public void preUpdate(Destination destination) {
        destination.setUpdatedAt(new Date());
    }
}
