package com.airport.manager.project;

import com.airport.manager.project.features.airplane.enums.AirplaneStatusEnum;
import com.airport.manager.project.features.airplane.models.AirplaneStatus;
import com.airport.manager.project.features.airplane.repositories.AirplaneStatusRepository;
import com.airport.manager.project.features.flight.enums.FlightStatusEnum;
import com.airport.manager.project.features.flight.models.FlightStatus;
import com.airport.manager.project.features.flight.repositories.FlightStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
public class DataSeeder {
    private final AirplaneStatusRepository airplaneStatusRepository;
    private final FlightStatusRepository flightStatusRepository;
    @Autowired
    public DataSeeder(AirplaneStatusRepository airplaneStatusRepository, FlightStatusRepository flightStatusRepository) {
        this.airplaneStatusRepository = airplaneStatusRepository;
        this.flightStatusRepository = flightStatusRepository;
    }

    public void seedData() {
        seedAirplaneStatuses();
        seedFlightStatuses();

    }

    private void seedAirplaneStatuses() {
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

    private void seedFlightStatuses() {
        if (flightStatusRepository.count() == 0) {
            FlightStatus scheduled = new FlightStatus(null, FlightStatusEnum.SCHEDULED);
            FlightStatus delayed = new FlightStatus(null, FlightStatusEnum.DELAYED);
            FlightStatus cancelled = new FlightStatus(null, FlightStatusEnum.CANCELLED);
            FlightStatus boarding = new FlightStatus(null, FlightStatusEnum.BOARDING);
            FlightStatus inAir = new FlightStatus(null, FlightStatusEnum.IN_AIR);
            FlightStatus landing = new FlightStatus(null, FlightStatusEnum.LANDING);
            FlightStatus archive = new FlightStatus(null, FlightStatusEnum.ARCHIVE);
            FlightStatus wrongData = new FlightStatus(null, FlightStatusEnum.WRONG_DATA);

            flightStatusRepository.save(scheduled);
            flightStatusRepository.save(delayed);
            flightStatusRepository.save(cancelled);
            flightStatusRepository.save(boarding);
            flightStatusRepository.save(inAir);
            flightStatusRepository.save(landing);
            flightStatusRepository.save(archive);
            flightStatusRepository.save(wrongData);
        }
    }
}
