package com.dev.server.controllers.users;

import com.dev.server.controllers.BaseAPI.ValueResponse;
import com.dev.server.controllers.users.model.UserResponse;
import com.dev.server.dtos.users.UserId;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("app/users/{idUser}")
public interface UserAPI {

    @GetMapping
    ValueResponse<UserResponse> findById(@PathVariable String idUser);
}
