package com.dev.server.controllers.users;

import com.dev.server.controllers.BaseAPI.ListResponse;
import com.dev.server.controllers.BaseAPI.ValueResponse;
import com.dev.server.controllers.users.model.UserRequest;
import com.dev.server.controllers.users.model.UserResponse;
import org.springframework.web.bind.annotation.*;

@RequestMapping("app/users")
public interface UsersAPI {
    @GetMapping
    ListResponse<UserResponse> findAll();

    @PostMapping
    ValueResponse<UserResponse> addUser(@RequestBody UserRequest userRequest);

    @PostMapping("/{userPhoneNumber}")
    ValueResponse<UserResponse> createUser(@PathVariable String userPhoneNumber);
}
