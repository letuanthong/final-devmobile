package com.dev.server.controllers.accounts;

import com.dev.server.controllers.BaseAPI.ListResponse;
import com.dev.server.controllers.accounts.model.AccountResponse;
import com.dev.server.dtos.users.UserId;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("app/accounts/{idUser}")
public interface AccountAPI {
    @GetMapping
    ListResponse<AccountResponse> getAccountsUser(@PathVariable String idUser);
}
