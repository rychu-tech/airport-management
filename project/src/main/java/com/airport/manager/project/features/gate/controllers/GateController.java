package com.airport.manager.project.features.gate.controllers;

import com.airport.manager.project.features.gate.models.Gate;
import com.airport.manager.project.features.gate.models.GateDTO;
import com.airport.manager.project.features.gate.services.GateService;
import com.airport.manager.project.features.user.models.User;
import com.airport.manager.project.features.user.models.UserDetailsPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gates")
public class GateController {
    private final GateService gateService;
    public GateController(GateService gateService) {
        this.gateService = gateService;
    }
    @PostMapping
    public Gate addGate(
            @RequestBody GateDTO gateDTO,
            @AuthenticationPrincipal UserDetailsPrincipal userDetailsPrincipal
    )
    {
        User user = userDetailsPrincipal.getUser();
        return gateService.addGate(gateDTO, user);
    }
}
