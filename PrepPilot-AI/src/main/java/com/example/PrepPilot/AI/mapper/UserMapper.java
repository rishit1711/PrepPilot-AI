package com.example.PrepPilot.AI.mapper;

import com.example.PrepPilot.AI.dto.RegisterRequest;
import com.example.PrepPilot.AI.dto.UserResponse;
import com.example.PrepPilot.AI.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(RegisterRequest request);

    UserResponse toResponse(User user);
}
