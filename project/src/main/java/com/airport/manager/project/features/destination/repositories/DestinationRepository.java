package com.airport.manager.project.features.destination.repositories;

import com.airport.manager.project.features.destination.models.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DestinationRepository extends JpaRepository<Destination, Long> {
    Destination findByName(String name);
    List<Destination> findByNameAndIdNot(String name, Long id);
}
