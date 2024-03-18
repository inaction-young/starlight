CREATE TABLE `user_auth` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '',
  `uuid` bigint(20) NOT NULL COMMENT '',
  `password` varchar(128) NOT NULL COMMENT '',
  `status` varchar(32) NOT NULL DEFAULT 'NORMAL' COMMENT '',
  `is_deleted` tinyint NOT NULL DEFAULT 0 COMMENT '',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_uuid` (`uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=1 COMMENT='';

CREATE TABLE `user_element` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '',
  `uuid` bigint(20) NOT NULL COMMENT '',
  `element` varchar(128) NOT NULL COMMENT '',
  `type` varchar(64) NOT NULL COMMENT '',
  `is_deleted` tinyint NOT NULL DEFAULT 0 COMMENT '',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_uuid_type` (`uuid`, `type`)
) ENGINE=InnoDB AUTO_INCREMENT=1 COMMENT='';


CREATE TABLE `user_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '',
  `uuid` bigint(20) NOT NULL COMMENT '',
  `nickname` varchar(64) NOT NULL DEFAULT '' COMMENT '',
  `full_name` varchar(64) NOT NULL DEFAULT '' COMMENT '',
  `birthday` date NOT NULL DEFAULT '' COMMENT '',
  `gender` varchar(1) NOT NULL DEFAULT 'U' COMMENT '',
  `height` int(4) NOT NULL DEFAULT 0 COMMENT '',
  `weight` decimal(6, 2) NOT NULL DEFAULT 0 COMMENT '',
  `id_card` varchar(64) NOT NULL DEFAULT '' COMMENT '',
  `reside_address` varchar(256) NOT NULL DEFAULT '' COMMENT '',
  `graduation_school` varchar(128) NOT NULL DEFAULT '' COMMENT '',
  `graduation_major` varchar(128) NOT NULL DEFAULT '' COMMENT '',
  `is_deleted` tinyint NOT NULL DEFAULT 0 COMMENT '',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_uuid` (`uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=1 COMMENT='';

CREATE TABLE `user_more_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '',
  `uuid` bigint(20) NOT NULL COMMENT '',
  `household_register` varchar(64) NOT NULL DEFAULT '' COMMENT '',
  `job` varchar(64) NOT NULL DEFAULT '' COMMENT '',
  `career` varchar(64) NOT NULL DEFAULT '' COMMENT '',
  `matrimony` varchar(1) NOT NULL DEFAULT 'U' COMMENT '',
  `friend_reviews` varchar(1024) NOT NULL DEFAULT '' COMMENT '',
  `love_exp` varchar(1024) NOT NULL DEFAULT '' COMMENT '',
  `interest` varchar(1024) NOT NULL DEFAULT '' COMMENT '',
  `self_introduction` varchar(1024) NOT NULL DEFAULT '' COMMENT '',
  `is_deleted` tinyint NOT NULL DEFAULT 0 COMMENT '',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_uuid` (`uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=1 COMMENT='';


CREATE TABLE `user_task` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '',
  `uuid` bigint(20) NOT NULL COMMENT '',
  `task_id` bigint(20) NOT NULL COMMENT '',
  `task_name` varchar(128) NOT NULL COMMENT '',
  `status` varchar(32) NOT NULL DEFAULT 'NO_COMPLETE' COMMENT '',
  `is_deleted` tinyint NOT NULL DEFAULT 0 COMMENT '',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '',
  PRIMARY KEY (`id`),
  INDEX `idx_uuid` (`uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=1 COMMENT='';

CREATE TABLE `user_invite` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '',
  `uuid` bigint(20) NOT NULL COMMENT '',
  `invited_uuid` bigint(20) NOT NULL COMMENT '',
  `status` varchar(32) NOT NULL DEFAULT 'NO_ACTIVATE' COMMENT '',
  `is_deleted` tinyint NOT NULL DEFAULT 0 COMMENT '',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_uuid` (`uuid`, `invited_uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=1 COMMENT='';



