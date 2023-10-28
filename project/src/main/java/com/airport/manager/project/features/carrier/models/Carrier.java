package com.airport.manager.project.features.carrier.models;

import com.airport.manager.project.features.carrier.listeners.CarrierListener;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@EntityListeners(CarrierListener.class)
@Table(name="carriers")
public class Carrier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="name", unique=true)
    private String name;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = false)
    private Date updatedAt;
    @Column(name = "active", nullable = false)
    private Boolean active = true;

    public Carrier(String name, Boolean active) {
        this.name = name;
        this.active = active;
    }

    public Carrier(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Carrier(Long id, String name, Boolean active) {
        this.id = id;
        this.name = name;
        this.active = active;
    }
}
