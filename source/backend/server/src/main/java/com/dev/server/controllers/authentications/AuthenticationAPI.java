package com.dev.server.controllers.authentications;

import com.dev.server.controllers.BaseAPI.ValueResponse;
import com.dev.server.controllers.authentications.model.LoginRequest;
import com.dev.server.controllers.users.model.UserResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("app")
public interface AuthenticationAPI {
    @PostMapping("/login")
    ValueResponse<UserResponse> login(@RequestBody LoginRequest loginRequest);
}
