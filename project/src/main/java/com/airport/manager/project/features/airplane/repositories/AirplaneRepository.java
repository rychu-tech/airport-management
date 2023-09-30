package com.airport.manager.project.features.airplane.repositories;

import com.airport.manager.project.features.airplane.models.Airplane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirplaneRepository extends JpaRepository<Airplane, Long> {

}
