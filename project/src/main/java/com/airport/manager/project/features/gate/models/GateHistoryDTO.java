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
    private Long gate_id;
    private Long user_id;
    private String user_name;
    private String comment;
    private Date created_at;

    public GateHistoryDTO(Long gateId, Long userId, String comment) {
        this.gate_id = gateId;
        this.user_id = userId;
        this.comment = comment;
    }
}
