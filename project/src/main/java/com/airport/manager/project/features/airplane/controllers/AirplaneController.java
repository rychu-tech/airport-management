package com.airport.manager.project.features.airplane.controllers;

import com.airport.manager.project.features.airplane.helpers.AirplaneDTO;
import com.airport.manager.project.features.airplane.models.Airplane;
import com.airport.manager.project.features.airplane.services.AirplaneService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
