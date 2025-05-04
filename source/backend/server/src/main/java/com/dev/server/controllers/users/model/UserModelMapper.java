package com.dev.server.controllers.users.model;

import com.dev.server.dtos.users.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserModelMapper {
    UserResponse toResponse(User user);
}
