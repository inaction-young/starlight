CREATE TABLE `recharge_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '',
  `uuid` bigint(20) NOT NULL COMMENT '',
  `user_uuid` bigint(20) NOT NULL COMMENT '',
  `recharge_type` varchar(32) NOT NULL DEFAULT 'DIAMOND' COMMENT '',
  `recharge_count` bigint(20) NOT NULL COMMENT '',
  `order_amount` bigint(20) NOT NULL COMMENT '',
  `status` varchar(32) NOT NULL DEFAULT 'WAIT_PAY' COMMENT '',
  `pay_no` varchar(128) NOT NULL DEFAULT '' COMMENT '',
  `is_deleted` tinyint NOT NULL DEFAULT 0 COMMENT '',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_uuid` (`uuid`),
  INDEX `idx_user_uuid_status` (`user_uuid`, `status`),
  INDEX `idx_user_uuid` (`pay_no`)
) ENGINE=InnoDB AUTO_INCREMENT=1 COMMENT='';

CREATE TABLE `user_asset` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '',
  `user_uuid` bigint(20) NOT NULL COMMENT '',
  `asset_type` varchar(32) NOT NULL DEFAULT 'NORMAL' COMMENT '',
  `asset_count` bigint(20) NOT NULL COMMENT '',
  `is_deleted` tinyint NOT NULL DEFAULT 0 COMMENT '',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '',
  PRIMARY KEY (`id`),
  INDEX `idx_uuid_asset_type` (`uuid`, `asset_type`)
) ENGINE=InnoDB AUTO_INCREMENT=1 COMMENT='';

CREATE TABLE `asset_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '',
  `user_uuid` bigint(20) NOT NULL COMMENT '',
  `asset_type` varchar(32) NOT NULL DEFAULT 'DIAMOND' COMMENT '',
  `quantity` bigint(20) NOT NULL DEFAULT 0 COMMENT '',
  `cause` varchar(32) NOT NULL COMMENT '',
  `remarks` varchar(128) NOT NULL COMMENT '',
  `expires_at` bigint(20) NOT NULL DEFAULT 0 COMMENT '',
  `is_deleted` tinyint NOT NULL DEFAULT 0 COMMENT '',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '',
  PRIMARY KEY (`id`),
  INDEX `idx_uuid` (`uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=1 COMMENT='用户资产明细表';


