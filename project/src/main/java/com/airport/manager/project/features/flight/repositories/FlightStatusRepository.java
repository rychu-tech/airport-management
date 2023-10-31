package com.airport.manager.project.features.flight.repositories;

import com.airport.manager.project.features.flight.models.FlightStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightStatusRepository extends JpaRepository<FlightStatus, Long> {
}
