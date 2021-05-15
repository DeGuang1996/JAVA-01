package com.geek.web.service;

import com.geek.api.dto.AccountActionDTO;
import com.geek.api.dto.AccountOperationDTO;
import com.geek.api.dto.TransferDTO;
import com.geek.api.service.IAAccountService;
import com.geek.api.service.IBAccountService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class TransferServiceImpl implements TransferService {

    @DubboReference(version = "1.0.0.a")
    private IAAccountService accountServiceA;

    @DubboReference(version = "1.0.0.b")
    private IBAccountService accountServiceB;

    // private Map<String, IAAccountService> accountServiceMap;

    @Override
    @HmilyTCC(confirmMethod = "transferConfirm", cancelMethod = "transferCancel")
    public void transferByDubbo(TransferDTO transferDTO) {
        AccountOperationDTO accountOperationSource = buildAccountOperation(transferDTO.getBizSerial(), transferDTO.getSource());
        // IAAccountService sourceService = resolveService(transferDTO.getSource());

        AccountOperationDTO accountOperationTarget = buildAccountOperation(transferDTO.getBizSerial(), transferDTO.getTarget());
        // IAAccountService targetService = resolveService(transferDTO.getTarget());

        // sourceService.accountTry(accountOperationSource);
        // targetService.accountTry(accountOperationTarget);
        switch (transferDTO.getSource().getServiceName()) {
            case "accountServiceA":
                accountServiceA.accountTry(accountOperationSource);
                break;
            case "accountServiceB":
                accountServiceB.accountTry(accountOperationSource);
                break;
            default:
                break;
        }
        switch (transferDTO.getTarget().getServiceName()) {
            case "accountServiceA":
                accountServiceA.accountTry(accountOperationTarget);
                break;
            case "accountServiceB":
                accountServiceB.accountTry(accountOperationTarget);
                break;
            default:
                break;
        }
    }

    public void transferConfirm(TransferDTO transferDTO) {
        System.out.println("transferConfirm : " + transferDTO.getBizSerial());
    }

    public void transferCancel(TransferDTO transferDTO) {
        System.out.println("transferCancel : " + transferDTO.getBizSerial());
    }

    // private IAAccountService resolveService(AccountActionDTO accountActionDTO) {
    //     return accountServiceMap.get(accountActionDTO.getServiceName());
    // }

    private AccountOperationDTO buildAccountOperation(String bizSerial, AccountActionDTO accountActionDTO) {
        return AccountOperationDTO.builder()
                .bizSerial(bizSerial)
                .uid(accountActionDTO.getUid())
                .amount(accountActionDTO.getAmount())
                .accountType(accountActionDTO.getAccountType())
                .build();
    }

    // @Override
    // public void afterPropertiesSet() throws Exception {
    //     accountServiceMap = new HashMap<>(16);
    //     accountServiceMap.put("accountServiceA", accountServiceA);
    //     accountServiceMap.put("accountServiceB", accountServiceB);
    // }
}
