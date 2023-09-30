package com.airport.manager.project.features.airplane.helpers;

import com.airport.manager.project.features.airplane.models.Airplane;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AirplaneMapper {
    private final ModelMapper modelMapper;
    @Autowired
    public AirplaneMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        modelMapper.addMappings(new PropertyMap<Airplane, AirplaneDTO>() {
            @Override
            protected void configure() {
                map().setCarrier_name(source.getCarrier().getName());
                map().setCarrier_id(source.getCarrier().getId());
                map().setAirplane_status_id(source.getAirplaneStatus().getId());
                map().setAirplane_status_name(String.valueOf(source.getAirplaneStatus().getName()));
                map().setSeats_number(source.getSeatsNumber());
                map().setId(source.getId());


            }
        });
    }
    public AirplaneDTO mapToDTO(Airplane airplane) {
        return modelMapper.map(airplane, AirplaneDTO.class);
    }
}
