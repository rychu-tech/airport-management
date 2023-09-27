package com.airport.manager.project.features.carrier.controllers;

import com.airport.manager.project.features.carrier.models.Carrier;
import com.airport.manager.project.features.carrier.services.CarrierService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carriers")
public class CarrierController {

    private final CarrierService carrierService;

    public CarrierController(CarrierService carrierService) {
        this.carrierService = carrierService;
    }
    @PostMapping
    public Carrier addCarrier(@RequestBody Carrier carrier) {
        return carrierService.addCarrier(carrier);
    }

    @DeleteMapping("/{carrierId}")
    public Carrier deleteCarrier(@PathVariable Long carrierId) {
        return carrierService.deleteCarrierById(carrierId);

    }


}
