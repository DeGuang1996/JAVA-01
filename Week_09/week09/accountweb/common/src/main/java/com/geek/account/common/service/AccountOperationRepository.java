package com.geek.account.common.service;

import com.geek.dal.dao.TOrder;
import com.geek.dal.dao.TRmbAccount;
import com.geek.dal.dao.TUsdAccount;
import com.geek.dal.mapper.TOrderMapper;
import com.geek.dal.mapper.TRmbAccountMapper;
import com.geek.dal.mapper.TUsdAccountMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Slf4j
public class AccountOperationRepository {

    @Autowired
    private TRmbAccountMapper tRmbAccountMapper;

    @Autowired
    private TUsdAccountMapper tUsdAccountMapper;

    @Autowired
    private TOrderMapper tOrderMapper;

    @Transactional(rollbackFor = RuntimeException.class)
    public void doTry(TOrder tOrder) {
        switch (tOrder.getAccountType()) {
            case "RMB":
                TRmbAccount tRmbAccount = tRmbAccountMapper.selectAllByUid(tOrder.getUid());
                if (tRmbAccount.getAmount() + tOrder.getAmount() < 0) {
                    throw new RuntimeException("Insufficient account balance");
                }
                tOrderMapper.insert(tOrder);
                tRmbAccountMapper.updateByPrimaryKeyAndOrder(tRmbAccount, tOrder);
                break;
            case "USD":
                TUsdAccount tUsdAccount = tUsdAccountMapper.selectAllByUid(tOrder.getUid());
                if (tUsdAccount.getAmount() + tOrder.getAmount() < 0) {
                    throw new RuntimeException("Insufficient account balance");
                }
                tOrderMapper.insert(tOrder);
                tUsdAccountMapper.updateByPrimaryKeyAndOrder(tUsdAccount, tOrder);
                break;
            default:
                System.out.println(String.format("unknown accountType:{}", tOrder.getAccountType()));
                break;
        }
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public void doConfirm(TOrder order) {
        order.setFinished(true);
        tOrderMapper.updateStatus(order);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public void doCancel(TOrder tOrder) {
        tOrder.setFinished(true);
        tOrderMapper.updateStatus(tOrder);
        switch (tOrder.getAccountType()) {
            case "RMB":
                TRmbAccount tRmbAccount = tRmbAccountMapper.selectAllByUid(tOrder.getUid());
                tRmbAccountMapper.updateByPrimaryKeyAndOrder(tRmbAccount, tOrder);
                break;
            case "USD":
                TUsdAccount tUsdAccount = tUsdAccountMapper.selectAllByUid(tOrder.getUid());
                tUsdAccountMapper.updateByPrimaryKeyAndOrder(tUsdAccount, tOrder);
                break;
            default:
                System.out.println(String.format("unknown accountType:{}", tOrder.getAccountType()));
                break;
        }
    }
}
