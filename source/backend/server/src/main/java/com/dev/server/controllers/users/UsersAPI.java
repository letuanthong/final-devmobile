package com.dev.server.controllers.users;

import com.dev.server.controllers.BaseAPI.ListResponse;
import com.dev.server.controllers.users.model.UserResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("app/users")
public interface UsersAPI {
    @GetMapping
    ListResponse<UserResponse> findAll();
}
