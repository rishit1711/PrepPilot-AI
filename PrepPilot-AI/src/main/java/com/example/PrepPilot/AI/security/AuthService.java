package com.example.PrepPilot.AI.security;

import com.example.PrepPilot.AI.dto.LoginRequest;
import com.example.PrepPilot.AI.dto.RegisterRequest;
import com.example.PrepPilot.AI.dto.UserResponse;
import com.example.PrepPilot.AI.entity.User;
import com.example.PrepPilot.AI.entity.enums.Role;
import com.example.PrepPilot.AI.mapper.UserMapper;
import com.example.PrepPilot.AI.repository.UserRepository;
import com.example.PrepPilot.AI.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
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

    public String[] Login(LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword()));

        User user = (User) authentication.getPrincipal();
        String[] arr = new String[2];
        arr[0]= jwtService.GenerateAccessToken(user);
        arr[1]= jwtService.GenerateRefreshToken(user);

        return arr;

    }
}
