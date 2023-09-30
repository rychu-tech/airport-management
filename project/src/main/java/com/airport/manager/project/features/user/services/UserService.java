package com.airport.manager.project.features.user.services;

import com.airport.manager.project.features.user.models.Role;
import com.airport.manager.project.features.user.models.User;
import com.airport.manager.project.features.user.models.UserDTO;
import com.airport.manager.project.features.user.models.UserDetailsPrincipal;
import com.airport.manager.project.features.user.repositories.RoleRepository;
import com.airport.manager.project.features.user.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    public UserService(
            UserRepository userRepository,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder
    )
    {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User addUser(UserDTO userDTO) {
        User user = createUser(userDTO);
        String roleName = "CLIENT";
        Role role = roleRepository.findByName(roleName);
        if (role == null) {
            Role newRole = new Role();
            newRole.setName(roleName);
            role = addRole(newRole);
        }
        user.setRoles(Arrays.asList(role));
        return userRepository.save(user);
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<UserDTO> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map((user) -> mapToUserDTO(user))
                .collect(Collectors.toList());
    }

    private UserDTO mapToUserDTO(User user){
        UserDTO userDto = new UserDTO();
        String[] str = user.getName().split(" ");
        userDto.setFirst_name(str[0]);
        userDto.setLast_name(str[1]);
        userDto.setEmail(user.getEmail());
        return userDto;
    }

    private Role addRole(Role role){
        Role newRole = new Role();
        newRole.setName(role.getName());
        return roleRepository.save(newRole);
    }

    private User createUser(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getFirst_name() + " " + userDTO.getLast_name());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        return user;
    }




}
