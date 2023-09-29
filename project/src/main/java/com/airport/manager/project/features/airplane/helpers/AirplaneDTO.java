package com.airport.manager.project.features.airplane.helpers;

public class AirplaneDTO {
    private String name;
    private Integer seats_number;
    private Long carrier_id;
    private Long airplane_status_id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSeats_number() {
        return seats_number;
    }

    public void setSeats_number(Integer seats_number) {
        this.seats_number = seats_number;
    }

    public Long getCarrier_id() {
        return carrier_id;
    }

    public void setCarrier_id(Long carrier_id) {
        this.carrier_id = carrier_id;
    }

    public Long getAirplane_status_id() {
        return airplane_status_id;
    }

    public void setAirplane_status_id(Long airplane_status_id) {
        this.airplane_status_id = airplane_status_id;
    }
}
