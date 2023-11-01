package com.airport.manager.project.features.flight.models;

import com.airport.manager.project.features.airplane.models.Airplane;
import com.airport.manager.project.features.destination.models.Destination;
import com.airport.manager.project.features.flight.listeners.FlightListener;
import com.airport.manager.project.features.gate.models.Gate;
import com.airport.manager.project.features.user.models.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.internal.bytebuddy.implementation.bind.annotation.Default;

import java.util.Date;

@Entity
@Table(name = "flights")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(FlightListener.class)
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "code", unique = true)
    @NotEmpty
    private String code;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="flight_status_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private FlightStatus flightStatus;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "airplane_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Airplane airplane;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "destination_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Destination destination;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "gate_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Gate gate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "departure_time", nullable = false)
    private Date departureTime;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User user;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = false)
    private Date updatedAt;

    @Column(name = "active", nullable = false)
    private Boolean active = true;
}
