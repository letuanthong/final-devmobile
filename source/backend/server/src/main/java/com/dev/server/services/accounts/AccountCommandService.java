package com.dev.server.services.accounts;

import com.dev.server.repositories.accounts.AccountRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class AccountCommandService {
    @NonNull AccountRepository accountRepository;
}
