package com.dev.server.services.users;

import com.dev.server.dtos.users.User;
import com.dev.server.dtos.users.UserMapper;
import com.dev.server.repositories.users.UserEntity;
import com.dev.server.services.accounts.AccountUseCaseService;
import jakarta.transaction.Transactional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class UserUseCaseService {
    @NonNull UserCommandService userCommandService;
    @NonNull UserQueryService userQueryService;
    @NonNull UserMapper userMapper;
    @NonNull AccountUseCaseService accountUseCaseService;

    @Transactional
    public void addUser(User user) {
        userCommandService.save(user);
        accountUseCaseService.autoCreateAccount(user.idUser());
    }

    @Transactional
    public void update(String idUser, User user) {
        userCommandService.update(idUser, user);
    }

    @Transactional
    public void delete(String idUser) {
        userCommandService.delete(idUser);
    }

    @Transactional
    public User findById(String idUser) {
        return userQueryService.findById(idUser);
    }

    @Transactional
    public User findByPhoneNumber(String phoneNumber) {
        return userQueryService.findByPhoneNumber(phoneNumber);
    }

    @Transactional
    public List<User> findAll() {
        return userQueryService.findAll();
    }

    @Transactional
    public void createUser(String userPhoneNumber) {
        UserEntity userEntity = new UserEntity();
        userEntity.setIdUser(UUID.randomUUID().toString());
        userEntity.setUserFullName("None");
        userEntity.setUserGender("Male");
        userEntity.setUserDateOfBirth(Date.valueOf("2000-01-01"));
        userEntity.setUserIdentityNumber("None");
        userEntity.setUserIdentityIssuedDate(Date.valueOf("2000-01-01"));
        userEntity.setUserIdentityExpiresDate(Date.valueOf("2000-01-01"));
        userEntity.setUserIdentityIssuedPlace("None");
        userEntity.setUserIdentityExpiresPlace("None");
        userEntity.setUserPlaceOfOrigin("None");
        userEntity.setUserPlaceOfResidence("None");
        userEntity.setUserEmail("None");
        userEntity.setUserPassword("123456");
        userEntity.setUserPhoneNumber(userPhoneNumber);
        userEntity.setUserRole("customer");
        userEntity.setUserPicture("None");
        userEntity.setCreatedAt(LocalDateTime.now());
        userEntity.setUpdatedAt(LocalDateTime.now());
        addUser(userMapper.toDto(userEntity));
    }
}
