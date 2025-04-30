package com.dev.server.controllers.users;

import com.dev.server.controllers.BaseAPI.ListResponse;
import com.dev.server.controllers.users.model.UserResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UsersController implements UsersAPI {


    @Override
    public ListResponse<UserResponse> findAll() {
        // Implementation of the method to find all users
        return null; // Replace with actual implementation
    }
}
