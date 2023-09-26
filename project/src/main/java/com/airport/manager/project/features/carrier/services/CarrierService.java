package com.airport.manager.project.features.carrier.services;

import com.airport.manager.project.features.carrier.exceptions.CarrierNameExistsException;
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
    public Carrier addCarrier(Carrier carrier) throws CarrierNameExistsException {
        try {
            return carrierRepository.save(carrier);
        }
        catch (Exception e) {
            throw new CarrierNameExistsException("Carrier with such name exists!");
        }

    }
}
