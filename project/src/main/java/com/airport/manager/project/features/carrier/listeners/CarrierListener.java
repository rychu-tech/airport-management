package com.airport.manager.project.features.carrier.listeners;

import com.airport.manager.project.features.carrier.models.Carrier;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.util.Date;

public class CarrierListener {
    @PrePersist
    public void prePersist(Carrier carrier) {
        Date now = new Date();
        carrier.setCreatedAt(now);
        carrier.setUpdatedAt(now);
    }

    @PreUpdate
    public void preUpdate(Carrier carrier) {
        carrier.setUpdatedAt(new Date());
    }
}
