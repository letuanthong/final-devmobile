package com.dev.server.dtos.users;

import com.dev.server.repositories.users.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserEntity toEntity(User user);

    User toDto(UserEntity userEntity);
}
