package com.airport.manager.project.features.carrier.services;

import com.airport.manager.project.features.carrier.exceptions.CarrierNameExistsException;
import com.airport.manager.project.features.carrier.exceptions.CarrierNotFoundException;
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
        Carrier carrierLookup = carrierRepository.findByName(carrier.getName());
        if (carrierLookup != null) {
            throw new CarrierNameExistsException();
        }
        return carrierRepository.save(carrier);
    }

    public Carrier findById(Long carrierId) {
        return carrierRepository.findById(carrierId).orElse(null);
    }

    public Carrier deleteCarrierById(Long carrierId) {
        Carrier carrier = this.findById(carrierId);

        if (carrier == null) {
            throw new CarrierNotFoundException();
        }

        carrier.setActive(false);
        return carrierRepository.save(carrier);
    }
}
