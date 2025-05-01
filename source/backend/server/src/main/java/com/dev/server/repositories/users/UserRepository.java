package com.dev.server.repositories.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
    boolean existsByUserPhoneNumber(String phoneNumber);
    Optional<UserEntity> findByUserPhoneNumber(String phoneNumber);
}
