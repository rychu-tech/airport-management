package com.airport.manager.project.features.airplane.models;

import com.airport.manager.project.features.airplane.enums.AirplaneStatusEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="airplane_statuses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AirplaneStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name="name")
    private AirplaneStatusEnum name;
}
