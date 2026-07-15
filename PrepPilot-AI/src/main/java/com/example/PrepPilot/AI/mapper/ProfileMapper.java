package com.example.PrepPilot.AI.mapper;

import com.example.PrepPilot.AI.dto.CreateProfileRequest;
import com.example.PrepPilot.AI.dto.ProfileResponse;
import org.mapstruct.Mapper;
import org.springframework.context.annotation.Profile;

@Mapper(componentModel = "spring")

public interface ProfileMapper {
    Profile toEntity(CreateProfileRequest proileRequest);
    ProfileResponse toProfileResponse(Profile profile);
}
