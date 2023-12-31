package com.airport.manager.project.features.carrier.helpers;

import com.airport.manager.project.features.carrier.exceptions.CarrierNameExistsException;
import com.airport.manager.project.features.carrier.exceptions.CarrierNotActiveException;
import com.airport.manager.project.features.carrier.exceptions.CarrierNotFoundException;
import com.airport.manager.project.features.carrier.models.Carrier;
import com.airport.manager.project.features.carrier.repositories.CarrierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CarrierChecker {
    private final CarrierRepository carrierRepository;
    @Autowired
    public CarrierChecker(CarrierRepository carrierRepository) {
        this.carrierRepository = carrierRepository;
    }
    public void checkCarrierName(String carrierName) throws CarrierNameExistsException {
        Carrier carrierLookup = carrierRepository.findByName(carrierName);
        if (carrierLookup != null) {
            throw new CarrierNameExistsException();
        }
    }

    public void checkCarrierName(Long carrierId, String carrierName) throws CarrierNameExistsException {
        List<Carrier> carrierLookup = carrierRepository.findByNameAndIdNot(carrierName, carrierId);
        if (!carrierLookup.isEmpty()) {
            throw new CarrierNameExistsException();
        }
    }
    public Carrier checkCarrierId(Long carrierId) throws CarrierNotFoundException {
        Carrier carrier = carrierRepository.findById(carrierId).orElse(null);
        if (carrier == null) {
            throw new CarrierNotFoundException();
        }
        return carrier;
    }

    public void checkCarrierActive(Long carrierId) throws CarrierNotActiveException {
        Carrier carrier = carrierRepository.findById(carrierId).orElse(null);
        if (carrier != null && !carrier.getActive()) {
            throw new CarrierNotActiveException();
        }
    }
}
