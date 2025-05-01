package com.dev.server.services.users;

import com.dev.server.dtos.users.User;
import com.dev.server.dtos.users.UserId;
import com.dev.server.dtos.users.UserMapper;
import com.dev.server.exceptions.AppException;
import com.dev.server.exceptions.ErrorCode;
import com.dev.server.repositories.users.UserEntity;
import com.dev.server.repositories.users.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class UserQueryService {
    @NonNull UserRepository userRepository;
    @NonNull UserMapper userMapper;

    public User findById(UserId idUser) {
        return userRepository.findById(idUser.value())
                .map(userMapper::toDto)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
    }

    public User findByPhoneNumber(String phoneNumber) {
        return userRepository.findByUserPhoneNumber(phoneNumber)
                .map(userMapper::toDto)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
    }

    public List<User> findAll() {
        List<UserEntity> userEntities = userRepository.findAll();
        return userMapper.toDtos(userEntities);
    }
}
