package com.airport.manager.project.features.flight.repositories;

import com.airport.manager.project.features.flight.models.FlightHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightHistoryRepository extends JpaRepository<FlightHistory, Long> {
}
