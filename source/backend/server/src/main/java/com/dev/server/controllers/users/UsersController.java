package com.dev.server.controllers.users;

import com.dev.server.controllers.BaseAPI.ListResponse;
import com.dev.server.controllers.BaseAPI.ValueResponse;
import com.dev.server.controllers.users.model.UserModelMapper;
import com.dev.server.controllers.users.model.UserRequest;
import com.dev.server.controllers.users.model.UserResponse;
import com.dev.server.dtos.users.User;
import com.dev.server.services.users.UserUseCaseService;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UsersController implements UsersAPI {
    @NonNull UserUseCaseService userUseCaseService;
    @NonNull UserModelMapper userModelMapper;

    @Override
    public ListResponse<UserResponse> findAll() {
        return ListResponse.<UserResponse>builder()
                .code(200)
                .message("Get all users successfully")
                .data(userModelMapper.toResponses(userUseCaseService.findAll()))
                .build();
    }

    @Override
    public ValueResponse<UserResponse> addUser(UserRequest userRequest) {
        User user = userModelMapper.toDto(userRequest);
        userUseCaseService.addUser(user);
        return ValueResponse.<UserResponse>builder()
                .code(200)
                .message("Add user successfully")
                .data(userModelMapper.toResponse(user))
                .build();
    }

    @Override
    public ValueResponse<UserResponse> createUser(String userPhoneNumber) {
        userUseCaseService.createUser(userPhoneNumber);
        return ValueResponse.<UserResponse>builder()
                .code(200)
                .message("Create user successfully")
                .data(userModelMapper.toResponse(userUseCaseService.findByPhoneNumber(userPhoneNumber)))
                .build();
    }


}
