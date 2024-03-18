CREATE TABLE `dynamic` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_uuid` bigint(20) NOT NULL COMMENT '用户UUID',
  `content` varchar(1024) NOT NULL COMMENT '内容',
  `images` varchar(2048) NOT NULL DEFAULT '[]' COMMENT '配图',
  `position` varchar(128) NOT NULL DEFAULT '' COMMENT '位置坐标',
  `address` varchar(128) NOT NULL DEFAULT '' COMMENT '地址',
  `status` varchar(32) NOT NULL DEFAULT 'DRAFT' COMMENT '状态，DRAFT:草稿，RELEASE:发布',
  `is_deleted` tinyint NOT NULL DEFAULT 0 COMMENT '是否删除，0否1是',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  INDEX`idx_uuid` (`uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=1 COMMENT='动态表';

CREATE TABLE `dynamic_remarks` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `dynamic_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '动态ID',
  `dynamic_user_uuid` bigint(20) NOT NULL COMMENT '动态所属用户UUID',
  `previous_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '上一条评论ID, 0 为评论的楼主',
  `remarks` varchar(1024) NOT NULL COMMENT '评论',
  `image` varchar(512) NOT NULL DEFAULT '' COMMENT '评论图',
  `is_deleted` tinyint NOT NULL DEFAULT 0 COMMENT '是否删除，0否1是',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  INDEX`idx_uuid` (`uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=1 COMMENT='动态评论表';

CREATE TABLE `dynamic_support` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `dynamic_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '动态ID',
  `dynamic_user_uuid` bigint(20) NOT NULL COMMENT '动态所属用户UUID',
  `support_user_uuid` bigint(20) NOT NULL COMMENT '点赞用户',
  `is_deleted` tinyint NOT NULL DEFAULT 0 COMMENT '是否删除，0否1是',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  INDEX`idx_uuid` (`uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=1 COMMENT='动态点赞表';