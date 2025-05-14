package com.dev.server.controllers.otp;

import com.dev.server.controllers.BaseAPI.ValueResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("app/otp")
public interface OtpAPI {
    @GetMapping("/getOTP/{userPhoneNumber}")
    ValueResponse<String> getOTP(@PathVariable String userPhoneNumber);

}
