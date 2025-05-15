package com.dev.server.controllers.users.model;

import com.dev.server.dtos.users.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Mapper(componentModel = "spring")
public interface UserModelMapper {
    UserResponse toResponse(User user);
    List<UserResponse> toResponses(List<User> users);

    @Mapping(target = "userDateOfBirth", source = "userDateOfBirth", qualifiedByName = "stringToDate")
    @Mapping(target = "userIdentityIssuedDate", source = "userIdentityIssuedDate", qualifiedByName = "stringToDate")
    @Mapping(target = "userIdentityExpiresDate", source = "userIdentityExpiresDate", qualifiedByName = "stringToDate")
    User toDto(UserRequest userRequest);

    @Named("stringToDate")
    static Date stringToDate(String dateString) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            return formatter.parse(dateString);
        } catch (ParseException e) {
            throw new RuntimeException("Invalid date format: " + dateString);
        }
    }
}
