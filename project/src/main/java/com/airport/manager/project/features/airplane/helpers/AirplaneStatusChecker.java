package com.airport.manager.project.features.airplane.helpers;

import com.airport.manager.project.features.airplane.exceptions.AirplaneStatusNotFoundException;
import com.airport.manager.project.features.airplane.models.AirplaneStatus;
import com.airport.manager.project.features.airplane.repositories.AirplaneStatusRepository;
import org.springframework.stereotype.Component;

@Component
public class AirplaneStatusChecker {
    private final AirplaneStatusRepository airplaneStatusRepository;
    public AirplaneStatusChecker(AirplaneStatusRepository airplaneStatusRepository) {
        this.airplaneStatusRepository = airplaneStatusRepository;
    }
    public AirplaneStatus checkAirplaneStatusId(Long airplaneStatusId) throws  AirplaneStatusNotFoundException {
        AirplaneStatus airplaneStatus = airplaneStatusRepository.findById(airplaneStatusId).orElse(null);
        if (airplaneStatus == null) {
            throw new AirplaneStatusNotFoundException();
        }
        return airplaneStatus;
    }
}
