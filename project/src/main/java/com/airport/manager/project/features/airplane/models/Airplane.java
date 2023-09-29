package com.airport.manager.project.features.airplane.models;

import com.airport.manager.project.features.airplane.enums.AirplaneStatusEnum;
import com.airport.manager.project.features.carrier.models.Carrier;
import jakarta.persistence.*;

@Entity
@Table(name="airplanes")
public class Airplane {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="name")
    private String name;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="carrier_id")
    private Carrier carrier;

    @Column(name="seats_number")
    private Integer seatsNumber;

    @ManyToOne
    @JoinColumn(name="airplane_status_id")
    private AirplaneStatus airplaneStatus;

    public Airplane(String name, Carrier carrier, Integer seatsNumber, AirplaneStatus airplaneStatus) {
        this.name = name;
        this.carrier = carrier;
        this.seatsNumber = seatsNumber;
        this.airplaneStatus = airplaneStatus;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Carrier getCarrier() {
        return carrier;
    }

    public Integer getSeatsNumber() {
        return seatsNumber;
    }

    public AirplaneStatus getAirplaneStatus() {
        return airplaneStatus;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCarrier(Carrier carrier) {
        this.carrier = carrier;
    }

    public void setSeatsNumber(Integer seatsNumber) {
        this.seatsNumber = seatsNumber;
    }

    public void setAirplaneStatus(AirplaneStatus airplaneStatus) {
        this.airplaneStatus = airplaneStatus;
    }
}
