CREATE TABLE `user_auth` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `uuid` bigint(20) NOT NULL COMMENT '用户唯一ID',
  `password` varchar(128) NOT NULL COMMENT '密码',
  `status` varchar(32) NOT NULL DEFAULT 'NORMAL' COMMENT '状态，NORMAL:正常，LOGOFF:注销',
  `is_deleted` tinyint NOT NULL DEFAULT 0 COMMENT '是否删除，0否1是',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_uuid` (`uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=1 COMMENT='用户建档表';

CREATE TABLE `user_element` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `uuid` bigint(20) NOT NULL COMMENT '用户uuid',
  `element` varchar(128) NOT NULL COMMENT '要素',
  `type` varchar(64) NOT NULL COMMENT '要素类型，MOBILE-手机，ACCOUNT-账号，WE_CHAT-微信，APPLE_ID-苹果ID',
  `is_deleted` tinyint NOT NULL DEFAULT 0 COMMENT '是否删除，0否1是',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_uuid_type` (`uuid`, `type`)
) ENGINE=InnoDB AUTO_INCREMENT=1 COMMENT='用户要素表';


CREATE TABLE `user_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `uuid` bigint(20) NOT NULL COMMENT '用户uuid',
  `nickname` varchar(64) NOT NULL DEFAULT '' COMMENT '昵称',
  `full_name` varchar(64) NOT NULL DEFAULT '' COMMENT '姓名',
  `birthday` date NOT NULL DEFAULT '' COMMENT '生日',
  `gender` varchar(1) NOT NULL DEFAULT 'U' COMMENT '性别，U-未知，M-男，F-女',
  `height` int(4) NOT NULL DEFAULT 0 COMMENT '身高 CM',
  `weight` decimal(6, 2) NOT NULL DEFAULT 0 COMMENT '体重 KG',
  `id_card` varchar(64) NOT NULL DEFAULT '' COMMENT '身份证',
  `reside_address` varchar(256) NOT NULL DEFAULT '' COMMENT '居住地',
  `graduation_school` varchar(128) NOT NULL DEFAULT '' COMMENT '毕业学校',
  `graduation_major` varchar(128) NOT NULL DEFAULT '' COMMENT '毕业专业',
  `is_deleted` tinyint NOT NULL DEFAULT 0 COMMENT '是否删除，0否1是',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_uuid` (`uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=1 COMMENT='用户信息表';

CREATE TABLE `user_more_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `uuid` bigint(20) NOT NULL COMMENT '用户uuid',
  `household_register` varchar(64) NOT NULL DEFAULT '' COMMENT '户籍',
  `job` varchar(64) NOT NULL DEFAULT '' COMMENT '工作（职业）',
  `career` varchar(64) NOT NULL DEFAULT '' COMMENT '工作经历',
  `matrimony` varchar(1) NOT NULL DEFAULT 'U' COMMENT '婚姻状况，U-未知，Y-已婚，N-未婚',
  `friend_reviews` varchar(1024) NOT NULL DEFAULT '' COMMENT '好友评价',
  `love_exp` varchar(1024) NOT NULL DEFAULT '' COMMENT '恋爱经历',
  `interest` varchar(1024) NOT NULL DEFAULT '' COMMENT '兴趣爱好',
  `self_introduction` varchar(1024) NOT NULL DEFAULT '' COMMENT '自我介绍',
  `is_deleted` tinyint NOT NULL DEFAULT 0 COMMENT '是否删除，0否1是',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_uuid` (`uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=1 COMMENT='用户更多信息表';


CREATE TABLE `user_task` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `uuid` bigint(20) NOT NULL COMMENT '用户uuid',
  `task_id` bigint(20) NOT NULL COMMENT '任务ID',
  `task_name` varchar(128) NOT NULL COMMENT '任务名称',
  `status` varchar(32) NOT NULL DEFAULT 'NO_COMPLETE' COMMENT '状态，COMPLETE:完成  PRIZE:奖励发放',
  `is_deleted` tinyint NOT NULL DEFAULT 0 COMMENT '是否删除，0否1是',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  INDEX `idx_uuid` (`uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=1 COMMENT='用户任务表';

CREATE TABLE `user_invite` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `uuid` bigint(20) NOT NULL COMMENT '用户uuid',
  `invited_uuid` bigint(20) NOT NULL COMMENT '被邀请的用户',
  `status` varchar(32) NOT NULL DEFAULT 'NO_ACTIVATE' COMMENT '状态，NO_ACTIVATE:未激活 ACTIVATE:激活  PRIZE:奖励发放',
  `is_deleted` tinyint NOT NULL DEFAULT 0 COMMENT '是否删除，0否1是',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_uuid` (`uuid`, `invited_uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=1 COMMENT='用户邀请表';



