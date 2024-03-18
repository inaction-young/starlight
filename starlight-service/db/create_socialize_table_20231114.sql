CREATE TABLE `dynamic` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '',
  `user_uuid` bigint(20) NOT NULL COMMENT '',
  `content` varchar(1024) NOT NULL COMMENT '',
  `images` varchar(2048) NOT NULL DEFAULT '[]' COMMENT '',
  `position` varchar(128) NOT NULL DEFAULT '' COMMENT '',
  `address` varchar(128) NOT NULL DEFAULT '' COMMENT '',
  `status` varchar(32) NOT NULL DEFAULT 'DRAFT' COMMENT '',
  `is_deleted` tinyint NOT NULL DEFAULT 0 COMMENT '',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '',
  PRIMARY KEY (`id`),
  INDEX`idx_uuid` (`uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=1 COMMENT='';

CREATE TABLE `dynamic_remarks` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '',
  `dynamic_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '',
  `dynamic_user_uuid` bigint(20) NOT NULL COMMENT '',
  `previous_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '',
  `remarks` varchar(1024) NOT NULL COMMENT '',
  `image` varchar(512) NOT NULL DEFAULT '' COMMENT '',
  `is_deleted` tinyint NOT NULL DEFAULT 0 COMMENT '',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '',
  PRIMARY KEY (`id`),
  INDEX`idx_uuid` (`uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=1 COMMENT='';

CREATE TABLE `dynamic_support` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '',
  `dynamic_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '',
  `dynamic_user_uuid` bigint(20) NOT NULL COMMENT '',
  `support_user_uuid` bigint(20) NOT NULL COMMENT '',
  `is_deleted` tinyint NOT NULL DEFAULT 0 COMMENT '',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '',
  PRIMARY KEY (`id`),
  INDEX`idx_uuid` (`uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=1 COMMENT='';