package com.airport.manager.project.features.carrier.services;

import com.airport.manager.project.features.carrier.exceptions.CarrierNameExistsException;
import com.airport.manager.project.features.carrier.exceptions.CarrierNotFoundException;
import com.airport.manager.project.features.carrier.helpers.CarrierChecker;
import com.airport.manager.project.features.carrier.models.Carrier;
import com.airport.manager.project.features.carrier.repositories.CarrierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarrierService {
    private CarrierRepository carrierRepository;
    private CarrierChecker carrierChecker;
    @Autowired
    public CarrierService(
            CarrierRepository carrierRepository,
            CarrierChecker carrierChecker
    )
    {
        this.carrierRepository = carrierRepository;
        this.carrierChecker = carrierChecker;
    }
    public Carrier addCarrier(Carrier carrier) throws CarrierNameExistsException {
        carrierChecker.checkCarrierName(carrier.getName());
        return carrierRepository.save(carrier);
    }

    public Carrier findById(Long carrierId) {
        return carrierRepository.findById(carrierId).orElse(null);
    }

    public Carrier deleteCarrierById(Long carrierId) {
        Carrier carrier = carrierChecker.checkCarrierId(carrierId);
        carrier.setActive(false);
        return carrierRepository.save(carrier);
    }

    public List<Carrier> findAll() {
        return carrierRepository.findAll();
    }

    public Carrier editCarrier(Long carrierId, Carrier carrierData) {
        carrierChecker.checkCarrierName(carrierId, carrierData.getName());
        Carrier carrier = carrierChecker.checkCarrierId(carrierId);
        carrier.setName(carrierData.getName());
        return carrierRepository.save(carrier);
    }
}
