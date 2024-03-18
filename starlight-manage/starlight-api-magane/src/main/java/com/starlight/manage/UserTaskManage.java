package com.starlight.manage;

import com.starlight.core.entity.bo.UserToken;
import com.starlight.entity.bo.user.UserTaskStatusBo;
import com.starlight.enums.user.UserTaskName;

import java.util.List;

/**
 * @ClassName: UserTaskManage
 * @Description:
 * @Author: Tj
 * @Date: 2023/11/10 下午3:39
 * @Version V1.0
 */
public interface UserTaskManage {

    boolean reward(UserToken userTokenBo, UserTaskName userTask);

    boolean complete(UserToken userTokenBo, UserTaskName userTask);

    List<UserTaskStatusBo> completeList(UserToken userTokenBo);
}
