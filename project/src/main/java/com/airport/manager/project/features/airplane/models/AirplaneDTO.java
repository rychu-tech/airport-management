package com.airport.manager.project.features.airplane.models;

public class AirplaneDTO {
    private Long id;
    private String name;
    private Integer seats_number;
    private Long carrier_id;
    private Long airplane_status_id;
    private String carrier_name;
    private String airplane_status_name;

    public String getCarrier_name() {
        return carrier_name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCarrier_name(String carrier_name) {
        this.carrier_name = carrier_name;
    }

    public String getAirplane_status_name() {
        return airplane_status_name;
    }

    public void setAirplane_status_name(String airplane_status_name) {
        this.airplane_status_name = airplane_status_name;
    }

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
