package com.airport.manager.project.features.flight.repositories;

import com.airport.manager.project.features.flight.models.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
}
