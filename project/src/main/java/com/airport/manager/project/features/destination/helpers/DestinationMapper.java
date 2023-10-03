package com.airport.manager.project.features.destination.helpers;

import com.airport.manager.project.features.airplane.models.Airplane;
import com.airport.manager.project.features.airplane.models.AirplaneDTO;
import com.airport.manager.project.features.destination.models.Destination;
import com.airport.manager.project.features.destination.models.DestinationDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DestinationMapper {
    private final ModelMapper modelMapper;
    @Autowired
    public DestinationMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        modelMapper.addMappings(new PropertyMap<Destination, DestinationDTO>() {
            @Override
            protected void configure() {
                map().setId(source.getId());
                map().setName(source.getName());
                map().setActive(source.getActive());
                map().setCreated_at(source.getCreatedAt());
                map().setUpdated_at(source.getUpdatedAt());
            }
        });
    }
    public DestinationDTO mapToDTO(Destination destination) {
        return modelMapper.map(destination, DestinationDTO.class);
    }
}
