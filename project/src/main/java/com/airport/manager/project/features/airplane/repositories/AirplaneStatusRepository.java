package com.airport.manager.project.features.airplane.repositories;

import com.airport.manager.project.features.airplane.models.AirplaneStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirplaneStatusRepository extends JpaRepository<AirplaneStatus, Long> {
}
