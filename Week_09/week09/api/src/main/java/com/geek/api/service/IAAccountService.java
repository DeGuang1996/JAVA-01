package com.geek.api.service;

import com.geek.api.dto.AccountOperationDTO;
import org.dromara.hmily.annotation.Hmily;

public interface IAAccountService {

    @Hmily
    void accountTry(AccountOperationDTO accountOperationDTO);
}
