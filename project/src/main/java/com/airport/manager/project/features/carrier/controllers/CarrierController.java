package com.airport.manager.project.features.carrier.controllers;

import com.airport.manager.project.ErrorResponse;
import com.airport.manager.project.features.carrier.exceptions.CarrierNameExistsException;
import com.airport.manager.project.features.carrier.models.Carrier;
import com.airport.manager.project.features.carrier.services.CarrierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@RestController
@RequestMapping("/api/carriers")
public class CarrierController {

    private CarrierService carrierService;
    @Autowired
    public CarrierController(CarrierService carrierService) {
        this.carrierService = carrierService;
    }
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addCarrier(@RequestBody Carrier carrier) {
        try {
            Carrier newCarrier = carrierService.addCarrier(carrier);
            return ResponseEntity.ok(newCarrier);
        }
        catch (CarrierNameExistsException e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new ErrorResponse(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY.value()));
        }

    }


}
