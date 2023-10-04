package com.airport.manager.project.features.destination.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DestinationDTO {
    private Long id;
    private String name;
    private Boolean active;
    private Date created_at;
    private Date updated_at;
}
