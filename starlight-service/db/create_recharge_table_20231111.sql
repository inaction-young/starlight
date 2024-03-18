CREATE TABLE `recharge_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `uuid` bigint(20) NOT NULL COMMENT '订单唯一ID',
  `user_uuid` bigint(20) NOT NULL COMMENT '用户UUID',
  `recharge_type` varchar(32) NOT NULL DEFAULT 'DIAMOND' COMMENT '充值类型，DIAMOND:钻石充值',
  `recharge_count` bigint(20) NOT NULL COMMENT '充值数量',
  `order_amount` bigint(20) NOT NULL COMMENT '金额(人命币：分)',
  `status` varchar(32) NOT NULL DEFAULT 'WAIT_PAY' COMMENT '状态，WAIT_PAY:待支付，PAY:已支付，CANCEL:取消，TIMEOUT:超时，COMPLETE：完成',
  `pay_no` varchar(128) NOT NULL DEFAULT '' COMMENT '第三方支付单号',
  `is_deleted` tinyint NOT NULL DEFAULT 0 COMMENT '是否删除，0否1是',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_uuid` (`uuid`),
  INDEX `idx_user_uuid_status` (`user_uuid`, `status`),
  INDEX `idx_user_uuid` (`pay_no`)
) ENGINE=InnoDB AUTO_INCREMENT=1 COMMENT='充值订单表';

CREATE TABLE `user_asset` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_uuid` bigint(20) NOT NULL COMMENT '用户uuid',
  `asset_type` varchar(32) NOT NULL DEFAULT 'NORMAL' COMMENT '资产类型，DIAMOND:钻石, COFFEE:咖啡',
  `asset_count` bigint(20) NOT NULL COMMENT '数量',
  `is_deleted` tinyint NOT NULL DEFAULT 0 COMMENT '是否删除，0否1是',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  INDEX `idx_uuid_asset_type` (`uuid`, `asset_type`)
) ENGINE=InnoDB AUTO_INCREMENT=1 COMMENT='用户资产表';

CREATE TABLE `asset_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_uuid` bigint(20) NOT NULL COMMENT '用户UUID',
  `asset_type` varchar(32) NOT NULL DEFAULT 'DIAMOND' COMMENT '资产类型，DIAMOND:钻石',
  `quantity` bigint(20) NOT NULL DEFAULT 0 COMMENT '数量，减为负数，加为正数',
  `cause` varchar(32) NOT NULL COMMENT '场景',
  `remarks` varchar(128) NOT NULL COMMENT '备注',
  `expires_at` bigint(20) NOT NULL DEFAULT 0 COMMENT '到期时间戳，0 永久有效',
  `is_deleted` tinyint NOT NULL DEFAULT 0 COMMENT '是否删除，0否1是',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  INDEX `idx_uuid` (`uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=1 COMMENT='用户资产明细表';


