package com.airport.manager.project.features.gate.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GateDTO {
    private Long id;
    private String name;
    private Boolean active;
    private Date created_at;
    private Date updated_at;

    private ArrayList<GateHistoryDTO> history;

    public GateDTO(String name) {
        this.name = name;
    }
}
