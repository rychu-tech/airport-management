package com.airport.manager.project.features.airplane.models;

import com.airport.manager.project.features.airplane.enums.AirplaneStatusEnum;
import jakarta.persistence.*;

@Entity
@Table(name="airplane_statuses")
public class AirplaneStatus {
    public AirplaneStatus() {}
    public AirplaneStatus(AirplaneStatusEnum enumValue) {
        this.setAirplaneStatus(enumValue);
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name="name")
    private AirplaneStatusEnum name;

    public Long getId() {
        return id;
    }

    public AirplaneStatusEnum getName() {
        return name;
    }

    public void setAirplaneStatus(AirplaneStatusEnum airplaneStatus) {
        this.name = airplaneStatus;
    }
}
