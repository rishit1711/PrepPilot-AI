package com.example.PrepPilot.AI.mapper;

import com.example.PrepPilot.AI.dto.CreateProfileRequest;
import com.example.PrepPilot.AI.dto.ProfileResponse;
import com.example.PrepPilot.AI.dto.UpdateProfileRequest;
import com.example.PrepPilot.AI.entity.Profile;
import org.mapstruct.Mapper;
;


@Mapper(componentModel = "spring")

public interface ProfileMapper {
    Profile toEntity(CreateProfileRequest proileRequest);
    ProfileResponse toProfileResponse(Profile profile);
    ProfileResponse toProfileResponse(UpdateProfileRequest profileRequest);
    Profile toEntity(ProfileResponse profileResponse);
}
