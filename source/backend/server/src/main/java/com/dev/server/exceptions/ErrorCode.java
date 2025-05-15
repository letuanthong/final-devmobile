package com.dev.server.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {
    ROLE_EXIST(404, "Vai trò đã tồn tại", HttpStatus.BAD_REQUEST),
    ROLE_NOT_FOUND(404, "Vai trò không tồn tại", HttpStatus.NOT_FOUND),
    USER_EXIST(404, "Người dùng đã tồn tại", HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(404, "Người dùng không tồn tại", HttpStatus.NOT_FOUND),
    INVALID_INPUT(400, "Dữ liệu đầu vào không hợp lệ", HttpStatus.BAD_REQUEST),
    SERVER_ERROR(500, "Lỗi máy chủ", HttpStatus.INTERNAL_SERVER_ERROR),
    UNAUTHENTICATED(401, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(403, "Unauthorized", HttpStatus.FORBIDDEN),
    INVALID_TOKEN(401, "Token không hợp lệ", HttpStatus.UNAUTHORIZED),
    TRANSACTION_NOT_FOUND(404, "Transaction không hợp lệ", HttpStatus.NOT_FOUND),
    INSUFFICIENT_BALANCE(400, "Số dư không đủ", HttpStatus.BAD_REQUEST),;



    final int code;
    final String message;
    final HttpStatusCode statusCode;
}
