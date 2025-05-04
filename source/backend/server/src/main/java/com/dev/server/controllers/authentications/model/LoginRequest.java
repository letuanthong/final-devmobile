package com.dev.server.controllers.authentications.model;

import lombok.Builder;

@Builder
public record LoginRequest(
        String userPhoneNumber,
        String userPassword
) {
}
