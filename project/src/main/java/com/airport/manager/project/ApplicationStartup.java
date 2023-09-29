package com.airport.manager.project;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartup {
    private final DataSeeder dataSeeder;
    public ApplicationStartup(DataSeeder dataSeeder) {
        this.dataSeeder = dataSeeder;
    }
    @EventListener(ApplicationReadyEvent.class)
    public void seedData() {
        dataSeeder.seedData();
    }
}
