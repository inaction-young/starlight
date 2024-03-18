package com.starlight.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.starlight.enums.user.UserInviteStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户邀请表
 * </p>
 *
 * @author Tj
 * @since 2023-11-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserInvite implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户uuid
     */
    private Long uuid;

    /**
     * 被邀请的用户
     */
    private Long invitedUuid;

    /**
     * 状态，NO_ACTIVATE:未激活 ACTIVATE:激活  PRIZE:奖励发放
     */
    private UserInviteStatus status;

    /**
     * 是否删除，0否1是
     */
    private Integer isDeleted;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    private LocalDateTime updatedAt;


}
