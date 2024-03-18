package com.starlight.api.vo.req.user;

import com.starlight.enums.user.UserTaskName;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @ClassName: TaskCompleteReq
 * @Description:
 * @Author: Tj
 * @Date: 2023/11/10 下午3:29
 * @Version V1.0
 */
@Data
public class UserTaskRewardReq {

    /**
     * 领取奖励的任务
     */
    @NotNull
    private UserTaskName task;

}
