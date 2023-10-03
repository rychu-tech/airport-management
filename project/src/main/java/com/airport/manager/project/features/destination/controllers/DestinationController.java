package com.airport.manager.project.features.destination.controllers;

import com.airport.manager.project.features.destination.models.Destination;
import com.airport.manager.project.features.destination.models.DestinationDTO;
import com.airport.manager.project.features.destination.services.DestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/destinations")
public class DestinationController {
    private final DestinationService destinationService;
    @Autowired
    public DestinationController(DestinationService destinationService) {
        this.destinationService = destinationService;
    }

    @PostMapping
    public Destination addDestination(@RequestBody DestinationDTO destinationDTO) {
        return destinationService.addDestination(destinationDTO);
    }

    @GetMapping
    public List<DestinationDTO> getDestinationList() {
        return destinationService.findAll();
    }

    @PutMapping("/{destinationId}")
    public Destination editDestination(@PathVariable Long destinationId, @RequestBody DestinationDTO destinationDTO) {
        return destinationService.editDestination(destinationDTO, destinationId);
    }

    @PatchMapping("/{destinationId}")
    public Destination restoreDestination(@PathVariable Long destinationId) {
        return destinationService.restoreDestinationById(destinationId);
    }

    @DeleteMapping("/{destinationId}")
    public Destination deleteDestination(@PathVariable Long destinationId) {
        return destinationService.deleteDestinationById(destinationId);
    }
}
