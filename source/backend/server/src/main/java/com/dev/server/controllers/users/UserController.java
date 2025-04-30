package com.dev.server.controllers.users;

import com.dev.server.controllers.BaseAPI.ValueResponse;
import com.dev.server.controllers.users.model.UserResponse;
import com.dev.server.dtos.users.UserId;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController implements UserAPI {


    @Override
    public ValueResponse<UserResponse> findById(UserId idUser) {
        return null;
    }
}
