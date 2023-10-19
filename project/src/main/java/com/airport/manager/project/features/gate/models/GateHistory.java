package com.airport.manager.project.features.gate.models;

import com.airport.manager.project.features.gate.listeners.GateHistoryListener;
import com.airport.manager.project.features.user.models.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "gate_histories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(GateHistoryListener.class)
public class GateHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="gate_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Gate gate;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User user;

    @Column(nullable = false)
    private String comment;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    public GateHistory(Gate gate, User user, String comment) {
        this.gate = gate;
        this.user = user;
        this.comment = comment;
    }
}
