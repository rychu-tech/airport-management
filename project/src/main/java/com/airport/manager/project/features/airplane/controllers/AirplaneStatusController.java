package com.airport.manager.project.features.airplane.controllers;

import com.airport.manager.project.features.airplane.models.AirplaneStatus;
import com.airport.manager.project.features.airplane.services.AirplaneStatusService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/airplane_statuses")
public class AirplaneStatusController {
    private final AirplaneStatusService airplaneStatusService;
    public AirplaneStatusController(AirplaneStatusService airplaneStatusService) {
        this.airplaneStatusService = airplaneStatusService;
    }

    @GetMapping
    public List<AirplaneStatus> getAirplaneStatusList() {
        return airplaneStatusService.findAll();
    }

}
