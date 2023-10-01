package com.airport.manager.project.features.airplane.services;

import com.airport.manager.project.features.airplane.enums.AirplaneStatusEnum;
import com.airport.manager.project.features.airplane.models.AirplaneStatus;
import com.airport.manager.project.features.airplane.repositories.AirplaneStatusRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AirplaneStatusServiceTest {
    @Mock
    private AirplaneStatusRepository airplaneStatusRepository;

    @InjectMocks
    private AirplaneStatusService airplaneStatusService;

    @Test
    public void findAll() {
        List<AirplaneStatus> airplaneStatuses = new ArrayList<>();
        airplaneStatuses.add(new AirplaneStatus(null, AirplaneStatusEnum.AVAILABLE));
        airplaneStatuses.add(new AirplaneStatus(null, AirplaneStatusEnum.IN_FLIGHT));
        when(airplaneStatusRepository.findAll()).thenReturn(airplaneStatuses);
        List<AirplaneStatus> foundAirplaneStatuses = airplaneStatusService.findAll();
        assertNotNull(foundAirplaneStatuses);
        assertEquals(airplaneStatuses.size(), foundAirplaneStatuses.size());
        assertTrue(foundAirplaneStatuses.containsAll(airplaneStatuses));
    }
}