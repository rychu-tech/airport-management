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

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="airplane_status_id")
    private AirplaneStatus airplaneStatus;
}
