package com.geek.api.dto;

import lombok.Data;

@Data
public class TransferDTO {

    /**
     * 交易流水号
     */
    private String bizSerial;

    /**
     * 源账户
     */
    private AccountActionDTO source;

    /**
     * 目标账户
     */
    private AccountActionDTO target;

}
