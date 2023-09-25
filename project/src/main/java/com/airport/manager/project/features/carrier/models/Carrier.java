package com.airport.manager.project.features.carrier.models;

import jakarta.persistence.*;

@Entity
@Table(name="carriers")
public class Carrier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="name", unique=true)
    private String name;
    private Boolean active = true;

    public Carrier() {}
    public Carrier(String name, Boolean active) {
        this.name = name;
        this.active = active;
    }
    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
