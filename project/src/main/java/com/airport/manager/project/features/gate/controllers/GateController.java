package com.airport.manager.project.features.gate.controllers;

import com.airport.manager.project.features.gate.models.Gate;
import com.airport.manager.project.features.gate.models.GateDTO;
import com.airport.manager.project.features.gate.requests.ChangeGateActiveRequest;
import com.airport.manager.project.features.gate.services.GateService;
import com.airport.manager.project.features.user.models.User;
import com.airport.manager.project.features.user.models.UserDetailsPrincipal;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{gateId}")
    public GateDTO getGate(@PathVariable Long gateId) {
        return gateService.getGateInDTOFormat(gateId);
    }

    @GetMapping
    public List<GateDTO> getGateList() {
        return gateService.getAllGatesInDTOFormat();
    }

    @PatchMapping("/{gateId}")
    public GateDTO changeGateActiveStatus(
            @PathVariable Long gateId,
            @RequestBody ChangeGateActiveRequest request,
            @AuthenticationPrincipal UserDetailsPrincipal userDetailsPrincipal
    )
    {
        User user = userDetailsPrincipal.getUser();
        gateService.changeGateActiveStatus(gateId, request.getActive(), request.getComment(), user);
        return gateService.getGateInDTOFormat(gateId);
    }
}
