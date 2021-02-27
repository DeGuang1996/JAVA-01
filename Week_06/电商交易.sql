-- ----------------------------
-- Table structure for emall_member
-- ----------------------------
DROP TABLE IF EXISTS `emall_member`;
CREATE TABLE `emall_member` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `gmt_create_time` datetime NOT NULL COMMENT '注册时间',
  `gmt_modified_time` datetime NOT NULL COMMENT '最后修改时间',
  `is_deleted` varchar(1) NOT NULL DEFAULT 'N' COMMENT '是否删除',

  `member_level` bigint(20) DEFAULT 1,
  `username` varchar(64) DEFAULT NULL COMMENT '用户名',
  `password` varchar(64) DEFAULT NULL COMMENT '密码',
  `nickname` varchar(64) DEFAULT NULL COMMENT '昵称',
  `phone` varchar(64) DEFAULT NULL COMMENT '手机号码',
  `status` int(1) DEFAULT 1 COMMENT '帐号启用状态:0->禁用；1->启用',

  `icon` varchar(500) DEFAULT NULL COMMENT '头像',
  `gender` int(1) DEFAULT 0 COMMENT '性别：0->未知；1->男；2->女',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `city` varchar(64) DEFAULT NULL COMMENT '城市',
  `job` varchar(100) DEFAULT NULL COMMENT '职业',
  `personalized_signature` varchar(200) DEFAULT NULL COMMENT '个性签名',
  `source_type` int(1) DEFAULT NULL COMMENT '用户来源',
  `integration` int(11) DEFAULT NULL COMMENT '积分',
  `growth` int(11) DEFAULT NULL COMMENT '成长值',
  
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_username` (`username`),
  UNIQUE KEY `idx_phone` (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员表';


-- ----------------------------
-- Table structure for emall_product
-- ----------------------------
DROP TABLE IF EXISTS `emall_product`;
CREATE TABLE `emall_product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `gmt_create_time` datetime NOT NULL COMMENT '注册时间',
  `gmt_modified_time` datetime NOT NULL COMMENT '最后修改时间',
  `is_deleted` varchar(1) NOT NULL DEFAULT 'N' COMMENT '是否删除',

  `brand_id` bigint(20) DEFAULT NULL COMMENT '品牌id',
  `brand_name` varchar(255) DEFAULT NULL COMMENT '品牌名称',
  `product_category_id` bigint(20) DEFAULT NULL COMMENT '商品分类id',
  `product_category_name` varchar(255) DEFAULT NULL COMMENT '商品分类名称',

  `name` varchar(64) NOT NULL,
  `pic` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `description` text COMMENT '商品描述',
  `price` decimal(10,2) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `sale` int(11) DEFAULT NULL COMMENT '销量',
  `unit` varchar(16) DEFAULT NULL COMMENT '单位',
  `weight` decimal(10,2) DEFAULT NULL COMMENT '商品重量，默认为克',
  `keywords` varchar(255) DEFAULT NULL,
  `stock` int(11) DEFAULT NULL COMMENT '库存',
  `low_stock` int(11) DEFAULT NULL COMMENT '库存预警值',
  `per_limit` int(11) DEFAULT NULL COMMENT '限购数量',

  `product_sn` varchar(64) NOT NULL COMMENT '货号',
  `publish_status` int(1) DEFAULT 1 COMMENT '上架状态：0->下架；1->上架',
  `new_status` int(1) DEFAULT 1 COMMENT '新品状态:0->不是新品；1->新品',
  `recommand_status` int(1) DEFAULT 0 COMMENT '推荐状态；0->不推荐；1->推荐',
  `verify_status` int(1) DEFAULT 0 COMMENT '审核状态：0->未审核；1->审核通过',
  
  `gift_growth` int(11) DEFAULT '0' COMMENT '赠送的成长值',
  `gift_point` int(11) DEFAULT '0' COMMENT '赠送的积分',
  `use_point_limit` int(11) DEFAULT NULL COMMENT '限制使用的积分数',
  `preview_status` int(1) DEFAULT NULL COMMENT '是否为预告商品：0->不是；1->是',
  
  `detail_html` text COMMENT '产品详情网页内容',
  `detail_mobile_html` text COMMENT '移动端网页详情',

  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品信息';


-- ----------------------------
-- Table structure for emall_order
-- ----------------------------
DROP TABLE IF EXISTS `emall_order`;
CREATE TABLE `emall_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `gmt_create_time` datetime NOT NULL COMMENT '注册时间',
  `gmt_modified_time` datetime NOT NULL COMMENT '最后修改时间',
  `is_deleted` varchar(1) NOT NULL DEFAULT 'N' COMMENT '是否删除',
  
  `member_id` bigint(20) NOT NULL COMMENT '下单用户id',
  `member_username` varchar(64) DEFAULT NULL COMMENT '用户名称',
  `member_nickname` varchar(64) DEFAULT NULL COMMENT '用户昵称',

  `total_amount` decimal(10,2) DEFAULT NULL COMMENT '订单总金额',
  `pay_amount` decimal(10,2) DEFAULT NULL COMMENT '应付金额（实际支付金额）',
  `freight_amount` decimal(10,2) DEFAULT NULL COMMENT '运费金额',
  `integration_amount` decimal(10,2) DEFAULT NULL COMMENT '积分抵扣金额',
  `use_integration` int(11) DEFAULT NULL COMMENT '下单时使用的积分',
  `integration` int(11) DEFAULT NULL COMMENT '可以获得的积分',
  `growth` int(11) DEFAULT NULL COMMENT '可以活动的成长值',
  `pay_type` int(1) DEFAULT NULL COMMENT '支付方式：0->未支付；1->支付宝；2->微信',
  `payment_time` datetime DEFAULT NULL COMMENT '支付时间',

  `source_type` int(1) DEFAULT NULL COMMENT '订单来源：0->PC订单；1->app订单',
  `status` int(1) DEFAULT NULL COMMENT '订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单',
  `delivery_company` varchar(64) DEFAULT NULL COMMENT '物流公司(配送方式)',
  `delivery_sn` varchar(64) DEFAULT NULL COMMENT '物流单号',
  
  `receiver_name` varchar(100) NOT NULL COMMENT '收货人姓名',
  `receiver_phone` varchar(32) NOT NULL COMMENT '收货人电话',
  `receiver_post_code` varchar(32) DEFAULT NULL COMMENT '收货人邮编',
  `receiver_province` varchar(32) DEFAULT NULL COMMENT '省份/直辖市',
  `receiver_city` varchar(32) DEFAULT NULL COMMENT '城市',
  `receiver_region` varchar(32) DEFAULT NULL COMMENT '区',
  `receiver_detail_address` varchar(200) DEFAULT NULL COMMENT '详细地址',
  `note` varchar(500) DEFAULT NULL COMMENT '订单备注',
  
  `auto_confirm_day` int(11) DEFAULT NULL COMMENT '自动确认时间（天）',
  `confirm_status` int(1) DEFAULT NULL COMMENT '确认收货状态：0->未确认；1->已确认',
  `delivery_time` datetime DEFAULT NULL COMMENT '发货时间',
  `receive_time` datetime DEFAULT NULL COMMENT '确认收货时间',
  `comment_time` datetime DEFAULT NULL COMMENT '评价时间',
  
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单表';
