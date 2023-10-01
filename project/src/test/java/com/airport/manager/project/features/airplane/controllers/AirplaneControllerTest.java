package com.airport.manager.project.features.airplane.controllers;

import com.airport.manager.project.features.airplane.enums.AirplaneStatusEnum;
import com.airport.manager.project.features.airplane.models.Airplane;
import com.airport.manager.project.features.airplane.models.AirplaneDTO;
import com.airport.manager.project.features.airplane.models.AirplaneStatus;
import com.airport.manager.project.features.airplane.repositories.AirplaneRepository;
import com.airport.manager.project.features.airplane.services.AirplaneService;
import com.airport.manager.project.features.carrier.models.Carrier;
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

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false, printOnlyOnFailure = false)
@Transactional
public class AirplaneControllerTest {
    @Autowired
    private ObjectMapper objectMapper;

    @InjectMocks
    private AirplaneController airplaneController;

    @Mock
    private AirplaneService airplaneService;

    @Mock
    private AirplaneRepository airplaneRepository;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    private static final String PATH = "/airplanes";
    @Test
    public void shouldAddAirplaneWith200() throws Exception {
        AirplaneDTO airplaneDTO = this.addAirplaneDTO();
        Airplane airplane = this.addAirplane();
        when(airplaneRepository.save(any(Airplane.class))).thenReturn(airplane);

        String requestBody = objectMapper.writeValueAsString(airplaneDTO);

        mockMvc.perform(post(PATH)
                        .contentType("application/json")
                        .accept("application/json")
                        .content(requestBody))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldGetAllAirplanesInDTOFormatWith200() throws Exception {
        when(airplaneService.getAllAirplanesInDTOFormat()).thenReturn(new ArrayList<>());
        mockMvc.perform(get(PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    private AirplaneDTO addAirplaneDTO() {
        AirplaneDTO airplaneDTO = new AirplaneDTO(
                "Test airplane DTO",
                100,
                1L,
                1L
        );
        return airplaneDTO;
    }

    private Airplane addAirplane() {
        return new Airplane(
                "Test airplane",
                new Carrier("Test carrier", true),
                100,
                new AirplaneStatus(1L, AirplaneStatusEnum.IN_FLIGHT)
        );
    }
}