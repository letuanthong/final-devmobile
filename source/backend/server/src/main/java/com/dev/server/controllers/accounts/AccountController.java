package com.dev.server.controllers.accounts;

import com.dev.server.controllers.BaseAPI.ListResponse;
import com.dev.server.controllers.accounts.model.AccountModelMapper;
import com.dev.server.controllers.accounts.model.AccountResponse;

import com.dev.server.services.accounts.AccountUseCaseService;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountController implements AccountAPI{
    @NonNull AccountUseCaseService accountUseCaseService;
    @NonNull AccountModelMapper accountModelMapper;
    @Override
    public ListResponse<AccountResponse> getAccountsUser(String idUser) {
        return ListResponse.<AccountResponse>builder()
                .code(200)
                .message("Success")
                .data(accountModelMapper.toResponses(accountUseCaseService.findByUserId(idUser)))
                .build();
    }
}
