package com.airport.manager.project.features.airplane.helpers;

import com.airport.manager.project.features.airplane.exceptions.AirplaneSeatsNumberInvalidException;
import com.airport.manager.project.features.airplane.models.Airplane;
import com.airport.manager.project.features.airplane.repositories.AirplaneRepository;
import com.airport.manager.project.features.airplane.repositories.AirplaneStatusRepository;
import com.airport.manager.project.features.carrier.exceptions.CarrierNotActiveException;
import com.airport.manager.project.features.carrier.helpers.CarrierChecker;
import com.airport.manager.project.features.carrier.models.Carrier;
import org.springframework.stereotype.Component;

@Component
public class AirplaneChecker {
    private final AirplaneRepository airplaneRepository;
    private final CarrierChecker carrierChecker;
    public AirplaneChecker(
            AirplaneRepository airplaneRepository,
            CarrierChecker carrierChecker
    )
    {
        this.airplaneRepository = airplaneRepository;
        this.carrierChecker = carrierChecker;
    }

    public void checkAirplaneSeatsNumber(Integer seatsNumber) throws AirplaneSeatsNumberInvalidException {
        if (seatsNumber <= 0) {
            throw new AirplaneSeatsNumberInvalidException();
        }
    }

    public void checkAirplaneActive(Long airplaneId) {
        Airplane airplane = airplaneRepository.findById(airplaneId).orElse(null);
        if (airplane != null) {
            carrierChecker.checkCarrierActive(airplane.getCarrier().getId());
        }
    }
}
