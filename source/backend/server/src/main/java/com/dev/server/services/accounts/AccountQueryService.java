package com.dev.server.services.accounts;

import com.dev.server.dtos.accounts.Account;
import com.dev.server.dtos.accounts.AccountMapper;
import com.dev.server.repositories.accounts.AccountRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class AccountQueryService {
    @NonNull AccountRepository accountRepository;
    @NonNull AccountMapper accountMapper;

    public List<Account> findByUserId(String userId) {
        return accountMapper.toDtos(accountRepository.findByIdUser(userId));
    }
}
