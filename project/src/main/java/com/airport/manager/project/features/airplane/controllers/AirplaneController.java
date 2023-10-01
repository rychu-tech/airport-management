package com.airport.manager.project.features.airplane.controllers;

import com.airport.manager.project.features.airplane.models.AirplaneDTO;
import com.airport.manager.project.features.airplane.models.Airplane;
import com.airport.manager.project.features.airplane.services.AirplaneService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/airplanes")
public class AirplaneController {
    private final AirplaneService airplaneService;
    public AirplaneController(AirplaneService airplaneService) {
        this.airplaneService = airplaneService;
    }

    @PostMapping
    public Airplane addAirplane(@RequestBody AirplaneDTO airplaneDTO) {
        return airplaneService.addAirplane(airplaneDTO);
    }

    @GetMapping
    public List<AirplaneDTO> getAllAirplanesInDTOFormat() {
        return airplaneService.getAllAirplanesInDTOFormat();
    }
}
