package com.dev.server.controllers.accounts.model;


import com.dev.server.dtos.accounts.Account;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountModelMapper {
    List<AccountResponse> toResponses(List<Account> accounts);
}
