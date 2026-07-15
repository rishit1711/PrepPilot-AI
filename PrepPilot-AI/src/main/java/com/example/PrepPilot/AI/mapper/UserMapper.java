package com.example.PrepPilot.AI.mapper;

import com.example.PrepPilot.AI.dto.RegisterRequest;
import com.example.PrepPilot.AI.dto.UserResponse;
import com.example.PrepPilot.AI.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "fullName", source = "fullName")
    User toEntity(RegisterRequest request);

    @Mapping(target = "fullName", source = "fullName")
    UserResponse toResponse(User user);
}