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

import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class UserCommandService {
    @NonNull UserRepository userRepository;
    @NonNull UserMapper userMapper;

    public void save(User user) {
        if (userRepository.existsByUserPhoneNumber(user.userPhoneNumber())){
            throw new AppException(ErrorCode.USER_EXIST);
        }
        UserEntity userEntity = userMapper.toEntity(user);
        userRepository.save(userEntity);
    }

    public void update(UserId idUser, User user) {
        UserEntity entity = userRepository.findById(idUser.value()).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        entity = userMapper.updateExist(user, entity);
        userRepository.save(entity);
    }

    public void delete(UserId idUser) {
        UserEntity entity = userRepository.findById(idUser.value()).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        userRepository.delete(entity);
    }
}
