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
public class FlightHistoryDTO {
    private Long id;
    private Long flight_id;
    private Long user_id;
    private String user_name;
    private String comment;
    private Date created_at;

    public FlightHistoryDTO(Long flightId, Long userId, String comment) {
        this.flight_id = flightId;
        this.user_id = userId;
        this.comment = comment;
    }
}
