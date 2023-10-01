package com.airport.manager.project.features.carrier.services;

import com.airport.manager.project.features.carrier.helpers.CarrierChecker;
import com.airport.manager.project.features.carrier.models.Carrier;
import com.airport.manager.project.features.carrier.repositories.CarrierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class CarrierService {
    private final CarrierRepository carrierRepository;
    private final CarrierChecker carrierChecker;
    @Autowired
    public CarrierService(
            CarrierRepository carrierRepository,
            CarrierChecker carrierChecker
    )
    {
        this.carrierRepository = carrierRepository;
        this.carrierChecker = carrierChecker;
    }
    public Carrier addCarrier(Carrier carrier) {
        carrierChecker.checkCarrierName(carrier.getName());
        return carrierRepository.save(carrier);
    }

    public Carrier deleteCarrierById(Long carrierId) {
        Carrier carrier = carrierChecker.checkCarrierId(carrierId);
        carrier.setActive(false);
        return carrierRepository.save(carrier);
    }

    public Carrier restoreCarrierById(Long carrierId) {
        Carrier carrier = carrierChecker.checkCarrierId(carrierId);
        carrier.setActive(true);
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
