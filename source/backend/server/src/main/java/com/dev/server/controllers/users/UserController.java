package com.dev.server.controllers.users;

import com.dev.server.controllers.BaseAPI.ValueResponse;
import com.dev.server.controllers.users.model.UserModelMapper;
import com.dev.server.controllers.users.model.UserResponse;
import com.dev.server.dtos.users.UserId;
import com.dev.server.services.users.UserUseCaseService;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController implements UserAPI {
    @NonNull UserUseCaseService userUseCaseService;
    @NonNull UserModelMapper userModelMapper;

    @Override
    public ValueResponse<UserResponse> findById(String idUser) {
        return ValueResponse.<UserResponse>builder()
                .code(200)
                .message("Get user by id successfully")
                .data(userModelMapper.toResponse(userUseCaseService.findById(idUser)))
                .build();
    }
}
