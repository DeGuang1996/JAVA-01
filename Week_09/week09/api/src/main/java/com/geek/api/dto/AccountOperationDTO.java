package com.geek.api.dto;

import com.geek.api.enums.AccountTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountOperationDTO implements Serializable {

    private static final long serialVersionUID = -607816881229116607L;

    /**
     * 交易流水号
     */
    private String bizSerial;

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
