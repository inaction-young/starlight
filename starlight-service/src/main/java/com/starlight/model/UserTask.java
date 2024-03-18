package com.starlight.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.starlight.enums.user.UserTaskName;
import com.starlight.enums.user.UserTaskStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户任务表
 * </p>
 *
 * @author Tj
 * @since 2023-11-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserTask implements Serializable {

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
     * 任务ID
     */
    private Long taskId;

    /**
     * 任务名称
     */
    private UserTaskName taskName;

    /**
     * 状态，COMPLETE:完成  PRIZE:奖励发放
     */
    private UserTaskStatus status;

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

    public static UserTask newborn(long uuid, UserTaskName taskName, UserTaskStatus status) {
        UserTask userTask = new UserTask();
        userTask.setUuid(uuid);
        userTask.setTaskId(taskName.getId());
        userTask.setTaskName(taskName);
        userTask.setStatus(status);
        return userTask;
    }

}
