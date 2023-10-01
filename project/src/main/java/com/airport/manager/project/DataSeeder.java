package com.airport.manager.project;

import com.airport.manager.project.features.airplane.enums.AirplaneStatusEnum;
import com.airport.manager.project.features.airplane.models.AirplaneStatus;
import com.airport.manager.project.features.airplane.repositories.AirplaneStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
public class DataSeeder {
    private final AirplaneStatusRepository airplaneStatusRepository;
    @Autowired
    public DataSeeder(AirplaneStatusRepository airplaneStatusRepository) {
        this.airplaneStatusRepository = airplaneStatusRepository;
    }

    public void seedData() {
        if (airplaneStatusRepository.count() == 0) {
            AirplaneStatus available = new AirplaneStatus(null, AirplaneStatusEnum.AVAILABLE);
            airplaneStatusRepository.save(available);
            AirplaneStatus inFlight = new AirplaneStatus(null, AirplaneStatusEnum.IN_FLIGHT);
            airplaneStatusRepository.save(inFlight);
            AirplaneStatus inService = new AirplaneStatus(null, AirplaneStatusEnum.IN_SERVICE);
            airplaneStatusRepository.save(inService);
            AirplaneStatus unavailable = new AirplaneStatus(null, AirplaneStatusEnum.UNAVAILABLE);
            airplaneStatusRepository.save(unavailable);
        }
    }
}
