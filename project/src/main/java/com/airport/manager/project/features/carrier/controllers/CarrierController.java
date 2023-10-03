package com.airport.manager.project.features.carrier.controllers;

import com.airport.manager.project.features.carrier.models.Carrier;
import com.airport.manager.project.features.carrier.services.CarrierService;
import com.airport.manager.project.features.user.models.UserDetailsPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public List<Carrier> getCarrierList() {
//        Viewing logged in user id
//        @AuthenticationPrincipal UserDetailsPrincipal userDetailsPrincipal)
//        System.out.println(userDetailsPrincipal.getId());
        return carrierService.findAll();
    }

    @PutMapping("/{carrierId}")
    public Carrier editCarrier(@PathVariable Long carrierId, @RequestBody Carrier carrier) {
        return carrierService.editCarrier(carrierId, carrier);
    }

    @PatchMapping("/{carrierId}")
    public Carrier restoreCarrier(@PathVariable Long carrierId) {
        return carrierService.restoreCarrierById(carrierId);
    }


}
