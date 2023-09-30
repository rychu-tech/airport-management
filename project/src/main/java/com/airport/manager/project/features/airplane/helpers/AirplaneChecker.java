package com.airport.manager.project.features.airplane.helpers;

import com.airport.manager.project.features.airplane.exceptions.AirplaneSeatsNumberInvalidException;
import com.airport.manager.project.features.airplane.repositories.AirplaneRepository;
import com.airport.manager.project.features.airplane.repositories.AirplaneStatusRepository;
import org.springframework.stereotype.Component;

@Component
public class AirplaneChecker {
    private final AirplaneRepository airplaneRepository;
    public AirplaneChecker(AirplaneRepository airplaneRepository) {
        this.airplaneRepository = airplaneRepository;
    }

    public void checkAirplaneSeatsNumber(Integer seatsNumber) throws AirplaneSeatsNumberInvalidException {
        if (seatsNumber <= 0) {
            throw new AirplaneSeatsNumberInvalidException();
        }
    }
}
