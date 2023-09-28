package com.airport.manager.project.features.carrier.helpers;

import com.airport.manager.project.features.carrier.exceptions.CarrierNameExistsException;
import com.airport.manager.project.features.carrier.exceptions.CarrierNotFoundException;
import com.airport.manager.project.features.carrier.models.Carrier;
import com.airport.manager.project.features.carrier.repositories.CarrierRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CarrierChecker {
    private CarrierRepository carrierRepository;
    public CarrierChecker(CarrierRepository carrierRepository) {
        this.carrierRepository = carrierRepository;
    }
    public void checkCarrierName(String carrierName) {
        Carrier carrierLookup = carrierRepository.findByName(carrierName);
        if (carrierLookup != null) {
            throw new CarrierNameExistsException();
        }
    }

    public void checkCarrierName(Long carrierId, String carrierName) {
        List<Carrier> carrierLookup = carrierRepository.findByNameAndIdNot(carrierName, carrierId);
        if (!carrierLookup.isEmpty()) {
            throw new CarrierNameExistsException();
        }
    }
    public Carrier checkCarrierId(Long carrierId) {
        Carrier carrier = carrierRepository.findById(carrierId).orElse(null);
        if (carrier == null) {
            throw new CarrierNotFoundException();
        }
        return carrier;
    }
}
