package com.dev.server.controllers.users;

import com.dev.server.controllers.BaseAPI.ValueResponse;
import com.dev.server.controllers.users.model.UserRequest;
import com.dev.server.controllers.users.model.UserResponse;

import org.springframework.web.bind.annotation.*;

@RequestMapping("app/users/{idUser}")
public interface UserAPI {
    @GetMapping
    ValueResponse<UserResponse> findById(@PathVariable String idUser);

    @PutMapping
    ValueResponse<UserResponse> update(@PathVariable String idUser, @RequestBody UserRequest userRequest);

    @DeleteMapping
    ValueResponse<String> delete(@PathVariable String idUser);
}
