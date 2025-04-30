package com.dev.server.dtos.accounts;

import com.dev.server.repositories.accounts.AccountEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    AccountEntity toEntity(Account account);

    Account toDto(AccountEntity accountEntity);
}
