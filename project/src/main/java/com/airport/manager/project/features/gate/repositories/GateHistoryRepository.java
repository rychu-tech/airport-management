package com.airport.manager.project.features.gate.repositories;

import com.airport.manager.project.features.gate.models.GateHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GateHistoryRepository extends JpaRepository<GateHistory, Long> {
    List<GateHistory> findByGateId(Long gateId);
}
