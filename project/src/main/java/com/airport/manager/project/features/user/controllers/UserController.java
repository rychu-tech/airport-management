package com.airport.manager.project.features.user.controllers;

import com.airport.manager.project.features.user.models.User;
import com.airport.manager.project.features.user.models.UserDTO;
import com.airport.manager.project.features.user.services.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/register")
    public User addUser(@RequestBody UserDTO userDTO) {
        return userService.addUser(userDTO);
    }
}
