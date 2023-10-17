package com.airport.manager.project.features.gate.models;

import com.airport.manager.project.features.user.models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GateHistoryDTO {
    private Long id;
    private Gate gate_id;
    private User user_id;
    private String comment;
    private Date created_at;

    public GateHistoryDTO(Gate gate, User user, String comment) {
        this.gate_id = gate;
        this.user_id = user;
        this.comment = comment;
    }
}
