package com.airport.manager.project.features.gate.repositories;

import com.airport.manager.project.features.gate.models.Gate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GateRepository extends JpaRepository<Gate, Long> {
    Gate findByName(String name);
}
