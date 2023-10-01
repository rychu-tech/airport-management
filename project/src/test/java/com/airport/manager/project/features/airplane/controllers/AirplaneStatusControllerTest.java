package com.airport.manager.project.features.airplane.controllers;

import com.airport.manager.project.features.airplane.enums.AirplaneStatusEnum;
import com.airport.manager.project.features.airplane.models.AirplaneStatus;
import com.airport.manager.project.features.airplane.repositories.AirplaneStatusRepository;
import com.airport.manager.project.features.airplane.services.AirplaneStatusService;
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

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false, printOnlyOnFailure = false)
@Transactional
public class AirplaneStatusControllerTest {
    @Autowired
    private ObjectMapper objectMapper;

    @InjectMocks
    private AirplaneStatusController airplaneStatusController;

    @Mock
    private AirplaneStatusService airplaneStatusService;

    @Mock
    private AirplaneStatusRepository airplaneStatusRepository;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    private static final String PATH = "/airplane_statuses";

    @Test
    public void shouldGetAirplaneStatusListWith200() throws Exception {
        List<AirplaneStatus> airplaneStatuses = Arrays.asList(
                new AirplaneStatus(null, AirplaneStatusEnum.IN_FLIGHT),
                new AirplaneStatus(null, AirplaneStatusEnum.AVAILABLE)
        );

        when(airplaneStatusService.findAll()).thenReturn(airplaneStatuses);

        mockMvc.perform(get(PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}