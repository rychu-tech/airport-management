package com.airport.manager.project;

import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.TimeZone;

@Configuration
public class ApplicationConfiguration {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @PostConstruct
    public void TimeZoneConfiguration() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }
}
