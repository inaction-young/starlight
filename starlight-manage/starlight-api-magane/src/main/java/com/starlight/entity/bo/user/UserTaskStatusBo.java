package com.starlight.entity.bo.user;

import com.starlight.enums.user.UserTaskName;
import com.starlight.enums.user.UserTaskStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: UserTaskListResp
 * @Description:
 * @Author: Tj
 * @Date: 2023/11/10 下午4:25
 * @Version V1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserTaskStatusBo {
    /**
     * 任务
     */
    private UserTaskName task;

    /**
     * 任务状态
     */
    private UserTaskStatus status;
}
