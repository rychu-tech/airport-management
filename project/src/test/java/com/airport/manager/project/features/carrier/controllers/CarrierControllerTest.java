package com.airport.manager.project.features.carrier.controllers;

import com.airport.manager.project.ObjectMapperConfig;
import com.airport.manager.project.features.carrier.helpers.CarrierChecker;
import com.airport.manager.project.features.carrier.models.Carrier;
import com.airport.manager.project.features.carrier.repositories.CarrierRepository;
import com.airport.manager.project.features.carrier.services.CarrierService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Import(ObjectMapperConfig.class)
public class CarrierControllerTest {

    @InjectMocks
    private CarrierController carrierController;

    @Mock
    private CarrierService carrierService;

    @Mock
    private CarrierRepository carrierRepository;

    @Mock
    private CarrierChecker carrierChecker;

    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(carrierController).build();

    }

    @Test
    public void testAddCarrier() throws Exception {
        Carrier carrier = new Carrier("TestCarrier", false);

        doNothing().when(carrierChecker).checkCarrierName("TestCarrier");
        when(carrierRepository.findByName("TestCarrier")).thenReturn(null);
        when(carrierService.addCarrier(carrier)).thenReturn(carrier);

        String requestBody = objectMapper.writeValueAsString(carrier);

        mockMvc.perform(MockMvcRequestBuilders.post("/carriers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldDeleteCarrierWith200() throws Exception {
        Long carrierId = 1L;

        Carrier savedCarrier = new Carrier(carrierId, "Saved Carrier");
        when(carrierChecker.checkCarrierId(carrierId)).thenReturn(savedCarrier);

        Carrier deletedCarrier = new Carrier("Deleted Carrier", false);
        when(carrierService.deleteCarrierById(carrierId)).thenReturn(deletedCarrier);

        mockMvc.perform(MockMvcRequestBuilders.delete("/carriers/{carrierId}", carrierId)
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

        mockMvc.perform(MockMvcRequestBuilders.get("/carriers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldEditCarrierWith200() throws Exception {
        Long carrierId = 1L;

        Carrier updatedCarrier = new Carrier(carrierId, "Saved Carrier", false);
        when(carrierChecker.checkCarrierId(carrierId)).thenReturn(updatedCarrier);

        when(carrierService.editCarrier(eq(updatedCarrier.getId()), any(Carrier.class))).thenReturn(updatedCarrier);

        String requestBody = objectMapper.writeValueAsString(updatedCarrier);

        mockMvc.perform(MockMvcRequestBuilders.put("/carriers/{carrierId}", updatedCarrier.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldRestoreCarrierWith200() throws Exception {
        Long carrierId = 1L;

        Carrier savedCarrier = new Carrier(carrierId, "Saved Carrier", false);
        when(carrierChecker.checkCarrierId(carrierId)).thenReturn(savedCarrier);

        Carrier restoredCarrier = new Carrier("Deleted Carrier", true);
        when(carrierService.deleteCarrierById(carrierId)).thenReturn(restoredCarrier);

        mockMvc.perform(MockMvcRequestBuilders.delete("/carriers/{carrierId}", carrierId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


}
