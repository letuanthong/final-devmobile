package com.dev.server.services.users;

import com.dev.server.dtos.users.User;
import com.dev.server.dtos.users.UserId;
import jakarta.transaction.Transactional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class UserUseCaseService {
    @NonNull UserCommandService userCommandService;
    @NonNull UserQueryService userQueryService;

    @Transactional
    public void save(User user) {
        userCommandService.save(user);
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
}
