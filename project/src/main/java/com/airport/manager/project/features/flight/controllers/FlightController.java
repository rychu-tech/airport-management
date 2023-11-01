package com.airport.manager.project.features.flight.controllers;

import com.airport.manager.project.features.flight.models.Flight;
import com.airport.manager.project.features.flight.models.FlightDTO;
import com.airport.manager.project.features.flight.requests.AddFlightRequest;
import com.airport.manager.project.features.flight.services.FlightService;
import com.airport.manager.project.features.user.models.User;
import com.airport.manager.project.features.user.models.UserDetailsPrincipal;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightController {
    private final FlightService flightService;
    public FlightController(
            FlightService flightService
    )
    {
        this.flightService = flightService;
    }

    @PostMapping
    public Flight addFlight(
            @RequestBody AddFlightRequest addFlightRequest,
            @AuthenticationPrincipal UserDetailsPrincipal userDetailsPrincipal
    )
    {
        User user = userDetailsPrincipal.getUser();
        FlightDTO flightDTO = addFlightRequest.parseToFlightDTO(user);
        return flightService.addFlight(flightDTO);
    }

    @GetMapping
    public List<FlightDTO> getAllFlightsInDTOFormat() {
        return flightService.getAllFlightsInDTOFormat();
    }

    @GetMapping("/{flightId}")
    public FlightDTO getFlight(@PathVariable Long flightId) {
        return flightService.getFlightInDTOFormat(flightId);
    }
}
