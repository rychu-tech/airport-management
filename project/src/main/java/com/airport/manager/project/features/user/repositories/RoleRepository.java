package com.airport.manager.project.features.user.repositories;

import com.airport.manager.project.features.user.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
