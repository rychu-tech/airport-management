package com.airport.manager.project.features.airplane.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AirplaneDTO {
    private Long id;
    private String name;
    private Integer seats_number;
    private Long carrier_id;
    private Long airplane_status_id;
    private String carrier_name;
    private String airplane_status_name;

    public AirplaneDTO(
        String name,
        Integer seatsNumber,
        Long carrierId,
        Long airplaneStatusId
    )
    {
        this.name = name;
        this.seats_number = seatsNumber;
        this.carrier_id = carrierId;
        this.airplane_status_id = airplaneStatusId;
    }
}
