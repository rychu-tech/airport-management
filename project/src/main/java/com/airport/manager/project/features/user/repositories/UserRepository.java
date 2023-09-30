package com.airport.manager.project.features.user.repositories;

import com.airport.manager.project.features.user.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
