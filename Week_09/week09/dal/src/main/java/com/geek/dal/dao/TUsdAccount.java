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
public class TUsdAccount {
    /**
    * 主键id
    */
    private Long id;

    /**
    * 用户id
    */
    private String uid;

    /**
    * 金额，单位分
    */
    private Long amount;

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