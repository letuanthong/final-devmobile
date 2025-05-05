package com.dev.server.dtos.accounts;

import com.dev.server.repositories.accounts.AccountEntity;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    AccountEntity toEntity(Account account);

    Account toDto(AccountEntity accountEntity);

    List<Account> toDtos(Collection<AccountEntity> accountEntities);
}
