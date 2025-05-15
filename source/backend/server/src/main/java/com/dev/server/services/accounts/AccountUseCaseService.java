package com.dev.server.services.accounts;

import com.dev.server.dtos.accounts.Account;
import com.dev.server.dtos.accounts.AccountMapper;
import com.dev.server.exceptions.AppException;
import com.dev.server.exceptions.ErrorCode;
import com.dev.server.repositories.accounts.AccountEntity;
import jakarta.transaction.Transactional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class AccountUseCaseService {
    @NonNull AccountQueryService accountQueryService;
    @NonNull AccountCommandService accountCommandService;
    @NonNull AccountMapper accountMapper;

    @Transactional
    public List<Account> findByUserId(String idUser) {
        return accountQueryService.findByUserId(idUser);
    }
    @Transactional
    public Account findByAccountNumber(String accountNumber) {
        return accountQueryService.findByAccountNumber(accountNumber);
    }

    public void addAmount(String accountNumber, BigDecimal amount) {
        AccountEntity account = accountMapper.toEntity(accountQueryService.findByAccountNumber(accountNumber));
        account.setAccountBalance(account.getAccountBalance().add(amount));
        accountCommandService.updateAccount(account);
    }

    public void subtractAmount(String accountNumber, BigDecimal amount) {
        AccountEntity account = accountMapper.toEntity(accountQueryService.findByAccountNumber(accountNumber));
        if (account.getAccountBalance().compareTo(amount) < 0) {
            throw new AppException(ErrorCode.INSUFFICIENT_BALANCE);
        }
        account.setAccountBalance(account.getAccountBalance().subtract(amount));
        accountCommandService.updateAccount(account);
    }

}
