package com.dev.server.services.authentication;

import com.dev.server.controllers.authentications.model.LoginRequest;
import com.dev.server.dtos.users.User;
import com.dev.server.dtos.users.UserMapper;
import com.dev.server.exceptions.AppException;
import com.dev.server.exceptions.ErrorCode;
import com.dev.server.repositories.users.UserRepository;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class AuthenticationService {
    @NonNull UserRepository userRepository;
    @NonNull UserMapper userMapper;

    public User login(LoginRequest loginRequest) {
        var user = userRepository.findByUserPhoneNumber(loginRequest.userPhoneNumber())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        boolean isMatch = user.getUserPassword().equals(loginRequest.userPassword());
        if (!isMatch) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }
        return userMapper.toDto(user);
    }
}
