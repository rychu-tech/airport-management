package com.airport.manager.project.features.destination.helpers;

import com.airport.manager.project.features.destination.exceptions.DestinationNameExistsException;
import com.airport.manager.project.features.destination.exceptions.DestinationNotFoundException;
import com.airport.manager.project.features.destination.models.Destination;
import com.airport.manager.project.features.destination.repositories.DestinationRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DestinationChecker {
    private final DestinationRepository destinationRepository;
    public DestinationChecker(DestinationRepository destinationRepository) {
        this.destinationRepository = destinationRepository;
    }

    public Destination checkDestinationId(Long destinationId) throws DestinationNotFoundException {
        Destination destination = destinationRepository.findById(destinationId).orElse(null);
        if (destination == null) {
            throw new DestinationNotFoundException();
        }
        return destination;
    }

    public void checkDestinationName(String name) throws DestinationNameExistsException {
        Destination destination = destinationRepository.findByName(name);
        if (destination != null) {
            throw new DestinationNameExistsException();
        }
    }

    public void checkDestinationName(String name, Long id) throws DestinationNameExistsException {
        List<Destination> destination = destinationRepository.findByNameAndIdNot(name, id);
        if (!destination.isEmpty()) {
            throw new DestinationNameExistsException();
        }
    }
}
