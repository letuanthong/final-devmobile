package com.dev.server.controllers.users;

import com.dev.server.controllers.BaseAPI.ListResponse;
import com.dev.server.controllers.users.model.UserModelMapper;
import com.dev.server.controllers.users.model.UserResponse;
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
}
