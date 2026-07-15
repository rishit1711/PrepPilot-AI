package com.example.PrepPilot.AI.service;

import com.example.PrepPilot.AI.entity.User;
import org.springframework.stereotype.Service;


public interface UserService {
    User getUserById(Long id);
}
