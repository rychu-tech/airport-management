package com.airport.manager.project.features.flight.requests;

import com.airport.manager.project.features.flight.models.FlightDTO;
import com.airport.manager.project.features.user.models.User;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddFlightRequest {
    @NotEmpty
    private String code;
    @NotEmpty
    private Long flight_status_id;
    @NotEmpty
    private Long airplane_id;
    @NotEmpty
    private Long destination_id;
    @NotEmpty
    private Long gate_id;
    @NotEmpty
    private Date departure_time;

    public FlightDTO parseToFlightDTO(User user) {
        FlightDTO flightDTO = new FlightDTO();
        flightDTO.setActive(true);
        flightDTO.setFlight_status_id(getFlight_status_id());
        flightDTO.setCode(getCode());
        flightDTO.setGate_id(getGate_id());
        flightDTO.setAirplane_id(getAirplane_id());
        flightDTO.setDeparture_time(getDeparture_time());
        flightDTO.setDestination_id(getDestination_id());
        flightDTO.setUser_id(user.getId());
        return flightDTO;
    }
}