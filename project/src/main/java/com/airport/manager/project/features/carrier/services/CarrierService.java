package com.airport.manager.project.features.carrier.services;

import com.airport.manager.project.features.carrier.models.Carrier;
import com.airport.manager.project.features.carrier.repositories.CarrierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarrierService {
    private CarrierRepository carrierRepository;
    @Autowired
    public CarrierService(CarrierRepository carrierRepository) {
        this.carrierRepository = carrierRepository;
    }
    public Carrier addCarrier(Carrier carrier) {
        return carrierRepository.save(carrier);
    }
}
