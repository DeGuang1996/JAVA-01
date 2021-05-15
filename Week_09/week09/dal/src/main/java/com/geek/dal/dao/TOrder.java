package com.geek.dal.dao;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TOrder {
    /**
     * 主键id
     */
    private Long id;

    /**
     * 用户id
     */
    private String uid;

    /**
     * 增加金额，单位分
     */
    private Long amount;

    /**
     * 交易流水号
     */
    private String bizSerial;

    /**
     * 账户类型RMB、USD
     */
    private String accountType;

    /**
     * 是否交易结束
     */
    private Boolean finished;

    /**
     * 版本号，乐观锁
     */
    private Integer edition;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 更新时间
     */
    private Date updatedTime;
}