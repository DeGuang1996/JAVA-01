delimiter //

drop procedure if EXISTS `createGroupEmallTables`;

create procedure createGroupEmallTables()

BEGIN

    DECLARE `@i` int(11);

    DECLARE `@createSql` VARCHAR(2560);

    set `@i`=0;

    WHILE  `@i`< 16 DO

            -- 创建表

            SET @createSql = CONCAT('CREATE TABLE IF NOT EXISTS emall_order_',`@i`,'(

   `id`                      bigint(20)   NOT NULL AUTO_INCREMENT,
    `gmt_create_time`         datetime     NOT NULL COMMENT ''注册时间'',
    `gmt_modified_time`       datetime     NOT NULL COMMENT ''最后修改时间'',
    `is_deleted`              varchar(1)   NOT NULL DEFAULT ''N'' COMMENT ''是否删除'',

    `member_id`               bigint(20)   NOT NULL COMMENT ''下单用户id'',
    `member_username`         varchar(64)           DEFAULT NULL COMMENT ''用户名称'',
    `member_nickname`         varchar(64)           DEFAULT NULL COMMENT ''用户昵称'',

    `total_amount`            decimal(10, 2)        DEFAULT NULL COMMENT ''订单总金额'',
    `pay_amount`              decimal(10, 2)        DEFAULT NULL COMMENT ''应付金额（实际支付金额）'',
    `freight_amount`          decimal(10, 2)        DEFAULT NULL COMMENT ''运费金额'',
    `integration_amount`      decimal(10, 2)        DEFAULT NULL COMMENT ''积分抵扣金额'',
    `use_integration`         int(11)               DEFAULT NULL COMMENT ''下单时使用的积分'',
    `integration`             int(11)               DEFAULT NULL COMMENT ''可以获得的积分'',
    `growth`                  int(11)               DEFAULT NULL COMMENT ''可以活动的成长值'',
    `pay_type`                int(1)                DEFAULT NULL COMMENT ''支付方式：0->未支付；1->支付宝；2->微信'',
    `payment_time`            datetime              DEFAULT NULL COMMENT ''支付时间'',

    `source_type`             int(1)                DEFAULT NULL COMMENT ''订单来源：0->PC订单；1->app订单'',
    `status`                  int(1)                DEFAULT NULL COMMENT ''订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单'',
    `delivery_company`        varchar(64)           DEFAULT NULL COMMENT ''物流公司(配送方式)'',
    `delivery_sn`             varchar(64)           DEFAULT NULL COMMENT ''物流单号'',

    `receiver_name`           varchar(100) NOT NULL COMMENT ''收货人姓名'',
    `receiver_phone`          varchar(32)  NOT NULL COMMENT ''收货人电话'',
    `receiver_post_code`      varchar(32)           DEFAULT NULL COMMENT ''收货人邮编'',
    `receiver_province`       varchar(32)           DEFAULT NULL COMMENT ''省份/直辖市'',
    `receiver_city`           varchar(32)           DEFAULT NULL COMMENT ''城市'',
    `receiver_region`         varchar(32)           DEFAULT NULL COMMENT ''区'',
    `receiver_detail_address` varchar(200)          DEFAULT NULL COMMENT ''详细地址'',
    `note`                    varchar(500)          DEFAULT NULL COMMENT ''订单备注'',

    `auto_confirm_day`        int(11)               DEFAULT NULL COMMENT ''自动确认时间（天）'',
    `confirm_status`          int(1)                DEFAULT NULL COMMENT ''确认收货状态：0->未确认；1->已确认'',
    `delivery_time`           datetime              DEFAULT NULL COMMENT ''发货时间'',
    `receive_time`            datetime              DEFAULT NULL COMMENT ''确认收货时间'',
    `comment_time`            datetime              DEFAULT NULL COMMENT ''评价时间'',

    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8'

                );

            prepare stmt from @createSql;

            execute stmt;

            SET `@i`= `@i`+1;

        END WHILE;

end //

delimiter ;

# 调用存储过程
CALL createGroupEmallTables();