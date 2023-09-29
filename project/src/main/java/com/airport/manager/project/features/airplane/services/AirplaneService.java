package com.airport.manager.project.features.airplane.services;

import com.airport.manager.project.features.airplane.helpers.AirplaneDTO;
import com.airport.manager.project.features.airplane.helpers.AirplaneStatusChecker;
import com.airport.manager.project.features.airplane.models.Airplane;
import com.airport.manager.project.features.airplane.models.AirplaneStatus;
import com.airport.manager.project.features.airplane.repositories.AirplaneRepository;
import com.airport.manager.project.features.carrier.helpers.CarrierChecker;
import com.airport.manager.project.features.carrier.models.Carrier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AirplaneService {
    private final AirplaneRepository airplaneRepository;
    private final AirplaneStatusChecker airplaneStatusChecker;
    private final CarrierChecker carrierChecker;
    @Autowired
    public AirplaneService(
            AirplaneRepository airplaneRepository,
            AirplaneStatusChecker airplaneStatusChecker,
            CarrierChecker carrierChecker
    )
    {
        this.airplaneRepository = airplaneRepository;
        this.airplaneStatusChecker = airplaneStatusChecker;
        this.carrierChecker = carrierChecker;
    }

    public Airplane addAirplane(AirplaneDTO airplaneData) {
        System.out.println(airplaneData.getAirplane_status_id());
        AirplaneStatus airplaneStatus = airplaneStatusChecker.checkAirplaneStatusId(airplaneData.getAirplane_status_id());
        Carrier carrier = carrierChecker.checkCarrierId(airplaneData.getCarrier_id());

        Airplane airplane = new Airplane(
                airplaneData.getName(),
                carrier,
                airplaneData.getSeats_number(),
                airplaneStatus
        );

        return airplaneRepository.save(airplane);
    }
}
