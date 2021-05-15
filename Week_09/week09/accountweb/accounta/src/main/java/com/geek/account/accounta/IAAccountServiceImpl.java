package com.geek.account.accounta;

import com.geek.account.common.service.AccountOperationRepository;
import com.geek.api.dto.AccountOperationDTO;
import com.geek.api.service.IAAccountService;
import com.geek.dal.dao.TOrder;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@DubboService(version = "1.0.0.a")
@Component
@Slf4j
public class IAAccountServiceImpl implements IAAccountService {

    @Autowired
    private AccountOperationRepository accountOperationRepository;

    @Override
    @HmilyTCC(confirmMethod = "actionConfirm", cancelMethod = "actionCancel")
    public void accountTry(AccountOperationDTO accountOperationDTO) {
        TOrder tOrder = buildOrderEntity(accountOperationDTO);
        accountOperationRepository.doTry(tOrder);
    }

    public void actionConfirm(AccountOperationDTO accountOperationDTO) {
        TOrder tOrder = buildOrderEntity(accountOperationDTO);
        accountOperationRepository.doConfirm(tOrder);
    }

    public void actionCancel(AccountOperationDTO accountOperationDTO) {
        TOrder tOrder = buildOrderEntity(accountOperationDTO);
        tOrder.setAmount(-tOrder.getAmount());
        accountOperationRepository.doCancel(tOrder);
    }

    private TOrder buildOrderEntity(AccountOperationDTO accountOperationDTO) {
        return TOrder.builder()
                .bizSerial(accountOperationDTO.getBizSerial())
                .uid(accountOperationDTO.getUid())
                .accountType(accountOperationDTO.getAccountType().name())
                .amount(accountOperationDTO.getAmount())
                .finished(false)
                .edition(1)
                .createdTime(new Date())
                .updatedTime(new Date())
                .build();
    }
}
