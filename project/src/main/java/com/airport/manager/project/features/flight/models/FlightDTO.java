package com.airport.manager.project.features.flight.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FlightDTO {
    private Long id;
    private String code;
    private Long flight_status_id;
    private String flight_status_name;
    private Long airplane_id;
    private String airplane_name;
    private Long destination_id;
    private String destination_name;
    private Long gate_id;
    private String gate_name;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    private Date departure_time;
    private Long user_id;
    private Boolean active;
    private ArrayList<FlightHistoryDTO> history;
}
