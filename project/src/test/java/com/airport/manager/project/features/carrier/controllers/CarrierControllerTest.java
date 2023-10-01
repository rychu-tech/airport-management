package com.airport.manager.project.features.carrier.controllers;

import com.airport.manager.project.features.carrier.models.Carrier;
import com.airport.manager.project.features.carrier.repositories.CarrierRepository;
import com.airport.manager.project.features.carrier.services.CarrierService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false, printOnlyOnFailure = false)
@Transactional
public class CarrierControllerTest {
    @Autowired private ObjectMapper objectMapper;
    @InjectMocks
    private CarrierController carrierController;

    @Mock
    private CarrierService carrierService;

    @Mock
    private CarrierRepository carrierRepository;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    private static final String PATH = "/carriers";

    @Test
    public void shouldAddCarrierWith200() throws Exception {
        Carrier carrier = new Carrier("Test carrier save", true);
        when(carrierRepository.save(any(Carrier.class))).thenReturn(carrier);
        String requestBody = objectMapper.writeValueAsString(carrier);

        mockMvc.perform(post(PATH)
                .contentType("application/json")
                .accept("application/json")
                .content(requestBody))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldDeleteCarrierWith200() throws Exception {
        Long carrierId = 1L;

        Carrier deletedCarrier = new Carrier("Deleted Carrier", false);
        when(carrierService.deleteCarrierById(carrierId)).thenReturn(deletedCarrier);

        mockMvc.perform(delete(PATH + "/{carrierId}", carrierId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldGetCarrierListWith200() throws Exception {
        List<Carrier> carrierList = Arrays.asList(
                new Carrier("Carrier 1", true),
                new Carrier("Carrier 2", true)
        );

        when(carrierService.findAll()).thenReturn(carrierList);

        mockMvc.perform(get(PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldEditCarrierWith200() throws Exception {
        Long carrierId = 1L;

        Carrier updatedCarrier = new Carrier("Updated Carrier", true);

        when(carrierService.editCarrier(eq(carrierId), any(Carrier.class))).thenReturn(updatedCarrier);

        String requestBody = objectMapper.writeValueAsString(updatedCarrier);

        mockMvc.perform(put(PATH + "/{carrierId}", carrierId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldRestoreCarrierWith200() throws Exception {
        Long carrierId = 1L;

        Carrier restoredCarrier = new Carrier("Restored Carrier", true);

        when(carrierService.restoreCarrierById(carrierId)).thenReturn(restoredCarrier);

        mockMvc.perform(patch(PATH + "/{carrierId}", carrierId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }




}