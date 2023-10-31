package com.airport.manager.project.features.flight.models;

import com.airport.manager.project.features.flight.listeners.FlightHistoryListener;
import com.airport.manager.project.features.user.models.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "flight_histories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(FlightHistoryListener.class)
public class FlightHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="flight_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Flight flight;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User user;

    @Column(nullable = false)
    private String comment;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    public FlightHistory(Flight flight, User user, String comment) {
        this.flight = flight;
        this.user = user;
        this.comment = comment;
    }
}
