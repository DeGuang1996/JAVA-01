package com.geek.api.dto;

import com.geek.api.enums.AccountTypeEnum;
import lombok.Data;

@Data
public class AccountActionDTO {

    /**
     * 服务名，简单起见，直接由客户端指定依赖的服务吧
     */
    private String serviceName;

    /**
     * 用户Id
     */
    private String uid;

    /**
     * 账户类型
     */
    private AccountTypeEnum accountType;

    /**
     * 金额增加值
     */
    private Long amount;

}
