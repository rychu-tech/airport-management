package com.airport.manager.project.features.user.controllers;

import com.airport.manager.project.features.user.models.User;
import com.airport.manager.project.features.user.models.UserDTO;
import com.airport.manager.project.features.user.requests.LoginRequest;
import com.airport.manager.project.features.user.services.UserService;
import com.airport.manager.project.security.TokenService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final TokenService tokenService;
    public UserController(
            UserService userService,
            TokenService tokenService
    )
    {
        this.userService = userService;
        this.tokenService = tokenService;
    }
    @PostMapping("/register")
    public User addUser(@RequestBody UserDTO userDTO) {
        return userService.addUser(userDTO);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        return tokenService.generateToken(loginRequest.getEmail());
    }

}
