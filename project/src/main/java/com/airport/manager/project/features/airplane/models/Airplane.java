package com.airport.manager.project.features.airplane.models;

import com.airport.manager.project.features.carrier.models.Carrier;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name="airplanes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Airplane {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="name")
    private String name;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="carrier_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Carrier carrier;

    @Column(name="seats_number")
    private Integer seatsNumber;

    @ManyToOne
    @JoinColumn(name="airplane_status_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private AirplaneStatus airplaneStatus;

    public Airplane(String name, Carrier carrier, Integer seatsNumber, AirplaneStatus airplaneStatus) {
        this.name = name;
        this.carrier = carrier;
        this.seatsNumber = seatsNumber;
        this.airplaneStatus = airplaneStatus;
    }
}
