package com.airport.manager.project.features.carrier.controllers;

import com.airport.manager.project.features.carrier.models.Carrier;
import com.airport.manager.project.features.carrier.services.CarrierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carriers")
public class CarrierController {

    private CarrierService carrierService;
    @Autowired
    public CarrierController(CarrierService carrierService) {
        this.carrierService = carrierService;
    }
    @PostMapping
    public Carrier addCarrier(@RequestBody Carrier carrier) {

        return carrierService.addCarrier(carrier);
    }
}
