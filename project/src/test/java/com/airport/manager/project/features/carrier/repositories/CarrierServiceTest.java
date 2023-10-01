package com.airport.manager.project.features.carrier.repositories;

import com.airport.manager.project.features.carrier.exceptions.CarrierNameExistsException;
import com.airport.manager.project.features.carrier.exceptions.CarrierNotFoundException;
import com.airport.manager.project.features.carrier.helpers.CarrierChecker;
import com.airport.manager.project.features.carrier.models.Carrier;
import com.airport.manager.project.features.carrier.services.CarrierService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CarrierServiceTest {
    @Mock
    private CarrierRepository carrierRepository;

    @Mock
    private CarrierChecker carrierChecker;

    @InjectMocks
    private CarrierService carrierService;

    @Test
    public void shouldSaveCarrier() {
        Carrier carrier = new Carrier();
        carrier.setName("ValidName");

        doNothing().when(carrierChecker).checkCarrierName(carrier.getName());
        when(carrierRepository.save(carrier)).thenReturn(carrier);

        Carrier result = carrierService.addCarrier(carrier);

        assertNotNull(result);
        verify(carrierChecker).checkCarrierName(carrier.getName());
        verify(carrierRepository).save(carrier);
    }

    @Test
    public void shouldThrowCarrierNameExistsOnSave() {
        Carrier carrier = new Carrier();
        carrier.setName("ExistingName");

        doThrow(new CarrierNameExistsException()).when(carrierChecker).checkCarrierName(carrier.getName());

        assertThrows(CarrierNameExistsException.class, () -> {
            carrierService.addCarrier(carrier);
        });

        verify(carrierChecker).checkCarrierName("ExistingName");
        verify(carrierRepository, never()).save(any(Carrier.class));
    }

    @Test
    public void shouldDeleteCarrier() {
        Long carrierId = 1L;
        Carrier carrier = new Carrier();
        when(carrierChecker.checkCarrierId(carrierId)).thenReturn(carrier);
        when(carrierRepository.save(carrier)).thenReturn(carrier);
        Carrier result = carrierService.deleteCarrierById(carrierId);
        assertFalse(result.getActive());
    }

    @Test
    public void shouldThrowCarrierNotFoundOnDelete() {
        Long carrierId = 1L;
        when(carrierChecker.checkCarrierId(carrierId)).thenThrow(new CarrierNotFoundException());
        assertThrows(CarrierNotFoundException.class, () -> {
            carrierService.deleteCarrierById(carrierId);
        });
        verify(carrierChecker).checkCarrierId(carrierId);
        verify(carrierRepository, never()).save(any(Carrier.class));
    }

    @Test
    public void shouldRestoreCarrier() {
        Long carrierId = 1L;
        Carrier carrier = new Carrier();
        when(carrierChecker.checkCarrierId(carrierId)).thenReturn(carrier);
        when(carrierRepository.save(carrier)).thenReturn(carrier);
        Carrier result = carrierService.restoreCarrierById(carrierId);
        assertTrue(result.getActive());
    }

    @Test
    public void shouldThrowCarrierNotFoundOnRestore() {
        Long carrierId = 1L;
        when(carrierChecker.checkCarrierId(carrierId)).thenThrow(new CarrierNotFoundException());
        assertThrows(CarrierNotFoundException.class, () -> {
            carrierService.restoreCarrierById(carrierId);
        });
        verify(carrierChecker).checkCarrierId(carrierId);
        verify(carrierRepository, never()).save(any(Carrier.class));
    }

    @Test
    public void shouldFindAll() {
        List<Carrier> expectedCarriers = new ArrayList<>();
        expectedCarriers.add(new Carrier("Carrier 1", true));
        expectedCarriers.add(new Carrier("Carrier 2", true));

        when(carrierRepository.findAll()).thenReturn(expectedCarriers);

        List<Carrier> result = carrierService.findAll();

        assertNotNull(result);
        assertEquals(expectedCarriers.size(), result.size());
        assertTrue(result.containsAll(expectedCarriers));
    }

    @Test
    public void shouldEditCarrier() {
        Long carrierId = 1L;
        Carrier carrierData = new Carrier();
        carrierData.setName("UpdatedName");

        Carrier existingCarrier = new Carrier("ExistingName", true);

        doNothing().when(carrierChecker).checkCarrierName(carrierId, carrierData.getName());
        when(carrierChecker.checkCarrierId(carrierId)).thenReturn(existingCarrier);
        when(carrierRepository.save(existingCarrier)).thenReturn(existingCarrier);

        Carrier result = carrierService.editCarrier(carrierId, carrierData);

        assertNotNull(result);
        assertEquals(carrierData.getName(), result.getName());
        verify(carrierChecker).checkCarrierName(carrierId, carrierData.getName());
        verify(carrierChecker).checkCarrierId(carrierId);
        verify(carrierRepository).save(existingCarrier);
    }

    @Test
    public void shouldThrowCarrierNameExistsOnEdit() {
        Long carrierId = 1L;
        Carrier carrierData = new Carrier();
        carrierData.setName("DuplicateName");

        doThrow(new CarrierNameExistsException()).when(carrierChecker).checkCarrierName(carrierId, carrierData.getName());

        assertThrows(CarrierNameExistsException.class, () -> {
            carrierService.editCarrier(carrierId, carrierData);
        });
    }

    @Test
    public void shouldThrowCarrierNotFoundOnEdit() {
        Long carrierId = 1L;
        Carrier carrierData = new Carrier();
        carrierData.setName("UpdatedName");

        doNothing().when(carrierChecker).checkCarrierName(carrierId, carrierData.getName());

        when(carrierChecker.checkCarrierId(carrierId)).thenThrow(new CarrierNotFoundException());

        assertThrows(CarrierNotFoundException.class, () -> {
            carrierService.editCarrier(carrierId, carrierData);
        });
    }
}