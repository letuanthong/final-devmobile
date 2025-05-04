package com.dev.server.controllers.authentications;

import com.dev.server.controllers.BaseAPI.ValueResponse;
import com.dev.server.controllers.authentications.model.LoginRequest;
import com.dev.server.controllers.users.model.UserModelMapper;
import com.dev.server.controllers.users.model.UserResponse;
import com.dev.server.services.authentication.AuthenticationService;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController implements AuthenticationAPI {
    @NonNull AuthenticationService authenticationService;
    @NonNull UserModelMapper userModelMapper;

    @Override
    public ValueResponse<UserResponse> login(LoginRequest loginRequest) {
        var user = authenticationService.login(loginRequest);
        return ValueResponse.<UserResponse>builder()
                .code(200)
                .message("Success")
                .data(userModelMapper.toResponse(user))
                .build();
    }
}
