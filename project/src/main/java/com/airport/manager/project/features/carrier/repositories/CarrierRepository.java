package com.airport.manager.project.features.carrier.repositories;

import com.airport.manager.project.features.carrier.models.Carrier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarrierRepository extends JpaRepository<Carrier, Long> {
    Carrier findByName(String name);
    List<Carrier> findByNameAndIdNot(String carrierName, Long carrierId);
}
