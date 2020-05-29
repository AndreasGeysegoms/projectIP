package com.example.planner.service;

import com.example.planner.dto.CreateUserDTO;
import com.example.planner.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserDTO createUser(CreateUserDTO userDTO);
}
