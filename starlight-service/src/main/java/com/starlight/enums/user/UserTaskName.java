package com.starlight.enums.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @ClassName: Task
 * @Description:
 * @Author: Tj
 * @Date: 2023/11/10 下午3:31
 * @Version V1.0
 */
@Getter
@AllArgsConstructor
public enum UserTaskName {

    /**
     * 新人引导
     */
    NEW_PEOPLE(1),

    /**
     * 见面任务
     */
    MEET(2),

    /**
     * 个人信息
     */
    PERSONAL_INFO(3),

    /**
     * 邀请好友
     */
    INVITED(4)

    ;

    private long id;

}
