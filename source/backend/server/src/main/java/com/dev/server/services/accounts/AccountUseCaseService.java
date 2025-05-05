package com.dev.server.services.accounts;

import com.dev.server.dtos.accounts.Account;
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
public class AccountUseCaseService {
    @NonNull AccountQueryService accountQueryService;
    @NonNull AccountCommandService accountCommandService;

    @Transactional
    public List<Account> findByUserId(String idUser) {
        return accountQueryService.findByUserId(idUser);
    }
}
