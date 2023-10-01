package com.airport.manager.project.features.airplane.services;

import com.airport.manager.project.features.airplane.models.AirplaneStatus;
import com.airport.manager.project.features.airplane.repositories.AirplaneStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirplaneStatusService {
    private final AirplaneStatusRepository airplaneStatusRepository;
    @Autowired
    public AirplaneStatusService(AirplaneStatusRepository airplaneStatusRepository) {
        this.airplaneStatusRepository = airplaneStatusRepository;
    }

    public List<AirplaneStatus> findAll() {
        List<AirplaneStatus> result = airplaneStatusRepository.findAll();
        return result;
    }
}
