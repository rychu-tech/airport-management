package com.airport.manager.project.features.gate.requests;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChangeGateActiveRequest {
    @NotEmpty
    private Boolean active;
    @NotEmpty
    private String comment;
}
