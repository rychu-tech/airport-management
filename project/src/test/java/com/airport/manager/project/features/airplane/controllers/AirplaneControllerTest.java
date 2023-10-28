package com.airport.manager.project.features.airplane.controllers;

import com.airport.manager.project.ObjectMapperConfig;
import com.airport.manager.project.features.airplane.helpers.AirplaneStatusChecker;
import com.airport.manager.project.features.airplane.models.Airplane;
import com.airport.manager.project.features.airplane.models.AirplaneDTO;
import com.airport.manager.project.features.airplane.models.AirplaneStatus;
import com.airport.manager.project.features.airplane.services.AirplaneService;
import com.airport.manager.project.features.carrier.helpers.CarrierChecker;
import com.airport.manager.project.features.carrier.models.Carrier;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@Import(ObjectMapperConfig.class)
public class AirplaneControllerTest {

    @InjectMocks
    private AirplaneController airplaneController;

    @Mock
    private AirplaneService airplaneService;
    @Mock
    private AirplaneStatusChecker airplaneStatusChecker;

    @Mock
    private CarrierChecker carrierChecker;
    @Autowired private ObjectMapper objectMapper;


    private MockMvc mockMvc;
    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(airplaneController).build();
    }

    @Test
    public void testAddAirplane() throws Exception {
        AirplaneDTO airplaneDTO = new AirplaneDTO();
        airplaneDTO.setName("TestAirplane");
        airplaneDTO.setCarrier_id(1L);
        airplaneDTO.setSeats_number(200);
        airplaneDTO.setAirplane_status_id(1L);

        AirplaneStatus airplaneStatus = new AirplaneStatus();
        Carrier carrier = new Carrier();

        when(airplaneStatusChecker.checkAirplaneStatusId(1L)).thenReturn(airplaneStatus);

        when(carrierChecker.checkCarrierId(1L)).thenReturn(carrier);

        Airplane sampleAirplane = new Airplane("TestAirplane", carrier, 200, airplaneStatus);
        when(airplaneService.addAirplane(airplaneDTO)).thenReturn(sampleAirplane);

        String requestBody = objectMapper.writeValueAsString(airplaneDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/airplanes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
                        .accept(MediaType.APPLICATION_JSON)
                )

                .andExpect(status().isOk());
    }
}
