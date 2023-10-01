package com.airport.manager.project.features.airplane.services;

import com.airport.manager.project.features.airplane.enums.AirplaneStatusEnum;
import com.airport.manager.project.features.airplane.exceptions.AirplaneSeatsNumberInvalidException;
import com.airport.manager.project.features.airplane.exceptions.AirplaneStatusNotFoundException;
import com.airport.manager.project.features.airplane.helpers.AirplaneChecker;
import com.airport.manager.project.features.airplane.helpers.AirplaneMapper;
import com.airport.manager.project.features.airplane.helpers.AirplaneStatusChecker;
import com.airport.manager.project.features.airplane.models.Airplane;
import com.airport.manager.project.features.airplane.models.AirplaneDTO;
import com.airport.manager.project.features.airplane.models.AirplaneStatus;
import com.airport.manager.project.features.airplane.repositories.AirplaneRepository;
import com.airport.manager.project.features.carrier.exceptions.CarrierNotFoundException;
import com.airport.manager.project.features.carrier.helpers.CarrierChecker;
import com.airport.manager.project.features.carrier.models.Carrier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AirplaneServiceTest {
    @Mock
    private AirplaneRepository airplaneRepository;

    @Mock
    private AirplaneStatusChecker airplaneStatusChecker;

    @Mock
    private CarrierChecker carrierChecker;

    @Mock
    private AirplaneChecker airplaneChecker;

    @Mock
    private AirplaneService airplaneService;

    @Mock
    private AirplaneMapper airplaneMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        airplaneService = new AirplaneService(
                airplaneRepository,
                airplaneStatusChecker,
                airplaneChecker,
                carrierChecker,
                airplaneMapper
        );
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
    @Test
    public void shouldAddAirplane() {
        AirplaneDTO airplaneDTO = this.addAirplaneDTO();
        AirplaneStatus airplaneStatus = new AirplaneStatus(1L, AirplaneStatusEnum.IN_FLIGHT);
        Carrier carrier = new Carrier(1L, "Test carrier");
        Airplane airplane = this.addAirplane();

        when(airplaneStatusChecker.checkAirplaneStatusId(any())).thenReturn(airplaneStatus);
        when(carrierChecker.checkCarrierId(any())).thenReturn(carrier);
        when(airplaneRepository.save(any(Airplane.class))).thenReturn(airplane);
        doNothing().when(airplaneChecker).checkAirplaneSeatsNumber(any());

        Airplane addedAirplane = airplaneService.addAirplane(airplaneDTO);

        assertNotNull(addedAirplane);
        verify(airplaneRepository).save(any(Airplane.class));
    }

    @Test
    public void shouldThrowAirplaneStatusNotFoundException() {
        AirplaneDTO airplaneDTO = this.addAirplaneDTO();

        doThrow(new AirplaneStatusNotFoundException()).when(airplaneStatusChecker).checkAirplaneStatusId(airplaneDTO.getAirplane_status_id());

        assertThrows(AirplaneStatusNotFoundException.class, () -> {
            airplaneService.addAirplane(airplaneDTO);
        });

        verify(airplaneStatusChecker).checkAirplaneStatusId(any());
        verify(airplaneRepository, never()).save(any(Airplane.class));
    }

    @Test
    public void shouldThrowCarrierNotFoundException() {
        AirplaneDTO airplaneDTO = this.addAirplaneDTO();

        when(carrierChecker.checkCarrierId(airplaneDTO.getCarrier_id()))
                .thenThrow(CarrierNotFoundException.class);

        assertThrows(CarrierNotFoundException.class, () -> {
            airplaneService.addAirplane(airplaneDTO);
        });

        verify(carrierChecker).checkCarrierId(airplaneDTO.getCarrier_id());
        verifyNoMoreInteractions(carrierChecker);
    }

    @Test
    public void shouldThrowAirplaneSeatsNumberInvalidException() {
        AirplaneDTO airplaneDTO = this.addAirplaneDTO();

        doThrow(new AirplaneSeatsNumberInvalidException()).when(airplaneChecker).checkAirplaneSeatsNumber(any());

        assertThrows(AirplaneSeatsNumberInvalidException.class, () -> {
            airplaneService.addAirplane(airplaneDTO);
        });

        verify(airplaneChecker).checkAirplaneSeatsNumber(airplaneDTO.getSeats_number());
        verifyNoMoreInteractions(airplaneChecker);
    }

    @Test
    public void shouldGetAllAirplanesInDTOFormat() {
        List<AirplaneDTO> expectedDTOs = new ArrayList<>();
        expectedDTOs.add(this.addAirplaneDTO());

        List<Airplane> airplanes = new ArrayList<>();
        airplanes.add(this.addAirplane());

        when(airplaneRepository.findAll()).thenReturn(airplanes);

        List<AirplaneDTO> result = airplaneService.getAllAirplanesInDTOFormat();

        assertNotNull(result);
    }
}