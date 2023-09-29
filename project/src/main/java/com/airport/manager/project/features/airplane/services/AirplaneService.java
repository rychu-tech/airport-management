package com.airport.manager.project.features.airplane.services;

import com.airport.manager.project.features.airplane.models.AirplaneStatus;
import com.airport.manager.project.features.airplane.repositories.AirplaneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AirplaneService {
    private final AirplaneRepository airplaneRepository;
    @Autowired
    public AirplaneService(AirplaneRepository airplaneRepository) {
        this.airplaneRepository = airplaneRepository;
    }
}
