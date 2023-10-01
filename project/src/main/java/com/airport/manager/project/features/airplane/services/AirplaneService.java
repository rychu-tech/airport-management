package com.airport.manager.project.features.airplane.services;

import com.airport.manager.project.features.airplane.helpers.AirplaneChecker;
import com.airport.manager.project.features.airplane.models.AirplaneDTO;
import com.airport.manager.project.features.airplane.helpers.AirplaneMapper;
import com.airport.manager.project.features.airplane.helpers.AirplaneStatusChecker;
import com.airport.manager.project.features.airplane.models.Airplane;
import com.airport.manager.project.features.airplane.models.AirplaneStatus;
import com.airport.manager.project.features.airplane.repositories.AirplaneRepository;
import com.airport.manager.project.features.carrier.helpers.CarrierChecker;
import com.airport.manager.project.features.carrier.models.Carrier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AirplaneService {
    private final AirplaneRepository airplaneRepository;
    private final AirplaneStatusChecker airplaneStatusChecker;
    private final CarrierChecker carrierChecker;
    private final AirplaneChecker airplaneChecker;
    private final AirplaneMapper airplaneMapper;
    @Autowired
    public AirplaneService(
            AirplaneRepository airplaneRepository,
            AirplaneStatusChecker airplaneStatusChecker,
            AirplaneChecker airplaneChecker,
            CarrierChecker carrierChecker,
            AirplaneMapper airplaneMapper
    )
    {
        this.airplaneRepository = airplaneRepository;
        this.airplaneStatusChecker = airplaneStatusChecker;
        this.carrierChecker = carrierChecker;
        this.airplaneChecker = airplaneChecker;
        this.airplaneMapper = airplaneMapper;
    }

    public Airplane addAirplane(AirplaneDTO airplaneData) {
        AirplaneStatus airplaneStatus = airplaneStatusChecker.checkAirplaneStatusId(airplaneData.getAirplane_status_id());
        Carrier carrier = carrierChecker.checkCarrierId(airplaneData.getCarrier_id());
        airplaneChecker.checkAirplaneSeatsNumber(airplaneData.getSeats_number());

        Airplane airplane = new Airplane(
                airplaneData.getName(),
                carrier,
                airplaneData.getSeats_number(),
                airplaneStatus
        );

        return airplaneRepository.save(airplane);
    }

    public List<AirplaneDTO> getAllAirplanesInDTOFormat() {
        List<Airplane> airplanes = airplaneRepository.findAll();
        return airplanes.stream()
                .map(airplaneMapper::mapToDTO)
                .collect(Collectors.toList());
    }
}
