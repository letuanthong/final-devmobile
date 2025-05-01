package com.dev.server.dtos.users;

import com.dev.server.repositories.users.UserEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserEntity toEntity(User user);

    User toDto(UserEntity userEntity);

    @Mapping(target = "idUser", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    UserEntity updateExist(User dto, @MappingTarget UserEntity entity);

    List<User> toDtos(List<UserEntity> entities);
}
