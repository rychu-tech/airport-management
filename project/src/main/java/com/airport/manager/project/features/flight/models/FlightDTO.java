package com.airport.manager.project.features.flight.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FlightDTO {
    private Long id;
    private String code;
    private Long flight_status_id;
    private Long airplane_id;
    private Long destination_id;
    private Long gate_id;
    private Date departure_time;
    private Long user_id;
    private Boolean active;
}
