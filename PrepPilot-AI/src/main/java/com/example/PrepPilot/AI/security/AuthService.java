package com.example.PrepPilot.AI.security;

import com.example.PrepPilot.AI.dto.RegisterRequest;
import com.example.PrepPilot.AI.dto.UserResponse;
import com.example.PrepPilot.AI.entity.User;
import com.example.PrepPilot.AI.entity.enums.Role;
import com.example.PrepPilot.AI.mapper.UserMapper;
import com.example.PrepPilot.AI.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    public UserResponse registerUser(RegisterRequest request) {


        if(userRepository.existsByEmail(request.getEmail())){
            throw new RuntimeException("User with this Email Already Registered");
        }

        User user = userMapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER);
        User savedUser=userRepository.save(user);

        return userMapper.toResponse(savedUser);
    }

}
