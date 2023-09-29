package com.airport.manager.project;

import com.airport.manager.project.features.airplane.enums.AirplaneStatusEnum;
import com.airport.manager.project.features.airplane.models.AirplaneStatus;
import com.airport.manager.project.features.airplane.repositories.AirplaneStatusRepository;
import com.airport.manager.project.features.airplane.services.AirplaneService;
import com.airport.manager.project.features.airplane.services.AirplaneStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DataSeeder {
    private final AirplaneStatusRepository airplaneStatusRepository;
    @Autowired
    public DataSeeder(AirplaneStatusRepository airplaneStatusRepository) {
        this.airplaneStatusRepository = airplaneStatusRepository;
    }

    public void seedData() {
        if (airplaneStatusRepository.count() == 0) {
            AirplaneStatus available = new AirplaneStatus(AirplaneStatusEnum.AVAILABLE);
            airplaneStatusRepository.save(available);
            AirplaneStatus inFlight = new AirplaneStatus(AirplaneStatusEnum.IN_FLIGHT);
            airplaneStatusRepository.save(inFlight);
            AirplaneStatus inService = new AirplaneStatus(AirplaneStatusEnum.IN_SERVICE);
            airplaneStatusRepository.save(inService);
            AirplaneStatus unavailable = new AirplaneStatus(AirplaneStatusEnum.UNAVAILABLE);
            airplaneStatusRepository.save(unavailable);
        }
    }
}
