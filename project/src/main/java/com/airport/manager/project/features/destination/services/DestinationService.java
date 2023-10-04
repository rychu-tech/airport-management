package com.airport.manager.project.features.destination.services;

import com.airport.manager.project.features.destination.helpers.DestinationChecker;
import com.airport.manager.project.features.destination.helpers.DestinationMapper;
import com.airport.manager.project.features.destination.models.Destination;
import com.airport.manager.project.features.destination.models.DestinationDTO;
import com.airport.manager.project.features.destination.repositories.DestinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DestinationService {
    private final DestinationRepository destinationRepository;
    private final DestinationChecker destinationChecker;
    private final DestinationMapper destinationMapper;
    @Autowired
    public DestinationService(
            DestinationRepository destinationRepository,
            DestinationChecker destinationChecker,
            DestinationMapper destinationMapper
    )
    {
        this.destinationRepository = destinationRepository;
        this.destinationChecker = destinationChecker;
        this.destinationMapper = destinationMapper;
    }

    public Destination deleteDestinationById(Long destinationId) {
        Destination destination = destinationChecker.checkDestinationId(destinationId);
        destination.setActive(false);
        return destinationRepository.save(destination);
    }

    public Destination restoreDestinationById(Long destinationId) {
        Destination destination = destinationChecker.checkDestinationId(destinationId);
        destination.setActive(true);
        return destinationRepository.save(destination);
    }

    public List<DestinationDTO> findAll() {
        List<Destination> destinations = destinationRepository.findAll();
        return destinations.stream()
                .map(destinationMapper::mapToDTO)
                .collect(Collectors.toList());
    }

    public Destination addDestination(DestinationDTO destinationDTO) {
        destinationChecker.checkDestinationName(destinationDTO.getName());
        Destination destination = new Destination(destinationDTO.getName());
        return destinationRepository.save(destination);
    }

    public Destination editDestination(DestinationDTO destinationDTO, Long destinationId) {
        destinationChecker.checkDestinationName(destinationDTO.getName(), destinationId);
        Destination destination = destinationChecker.checkDestinationId(destinationId);
        destination.setName(destinationDTO.getName());
        return destinationRepository.save(destination);
    }

}
