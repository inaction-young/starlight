package com.starlight.manage.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.starlight.cached.UserInfoCached;
import com.starlight.cached.UserInviteCached;
import com.starlight.cached.UserMoreInfoCached;
import com.starlight.cached.UserTaskCached;
import com.starlight.core.enums.ApiCode;
import com.starlight.core.exception.ApiException;
import com.starlight.core.utils.CollectorUtils;
import com.starlight.core.entity.bo.UserToken;
import com.starlight.entity.bo.user.UserTaskStatusBo;
import com.starlight.enums.user.UserTaskName;
import com.starlight.enums.user.UserTaskStatus;
import com.starlight.manage.UserTaskManage;
import com.starlight.model.UserInfo;
import com.starlight.model.UserMoreInfo;
import com.starlight.model.UserTask;
import com.starlight.service.UserTaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

/**
 * @ClassName: UserTaskManageImpl
 * @Description:
 * @Author: Tj
 * @Date: 2023/11/10 下午3:40
 * @Version V1.0
 */
@Log4j2
@Component
@RequiredArgsConstructor
public class UserTaskManageImpl implements UserTaskManage {

    private final UserInviteCached userInviteCached;

    private final UserInfoCached userInfoCached;

    private final UserMoreInfoCached userMoreInfoCached;

    private final UserTaskCached userTaskCached;


    private final UserTaskService userTaskService;


    private static final Map<UserTaskName, Function<UserToken, Boolean>> COMPLETE_HANDLER_MAP = Maps.newHashMap();

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public boolean reward(UserToken userTokenBo, UserTaskName taskName) {
        UserTask userTask = userTaskCached.getByTaskName(userTokenBo.getUuid(), taskName);
        if (Objects.isNull(userTask)) {
            throw new ApiException(ApiCode.API_TASK_NOT_COMPLETE);
        }

        if (UserTaskStatus.PRIZE == userTask.getStatus()) {
            return true;
        }

        try {
            //Todo: 发放钻石暂未实现


            userTask.setStatus(UserTaskStatus.PRIZE);
            return userTaskCached.updById(userTask);
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            throw new ApiException(e);
        }
    }

    /**
     * 邀请激活后、个人信息填写后、邀请见面后、新人引导后需要调用该方法
     * @param userTokenBo
     * @param taskName
     * @return
     */
    @Override
    public boolean complete(UserToken userTokenBo, UserTaskName taskName) {
        UserTask userTask = userTaskCached.getByTaskName(userTokenBo.getUuid(), taskName);
        if (Objects.nonNull(userTask)) {
            return true;
        }

        Boolean complete = isCompleteHandler(taskName).apply(userTokenBo);
        if (complete) {
            return userTaskService.save(UserTask.newborn(userTokenBo.getUuid(), taskName, UserTaskStatus.COMPLETE));
        }
        return false;
    }

    @Override
    public List<UserTaskStatusBo> completeList(UserToken userTokenBo) {
        List<UserTask> userTasks = userTaskCached.listByUuid(userTokenBo.getUuid());
        if (CollectorUtils.isEmpty(userTasks)) {
            return Lists.newArrayList();
        }
        return CollectorUtils.map(userTasks, t -> new UserTaskStatusBo(t.getTaskName(), t.getStatus()));
    }

    private Function<UserToken, Boolean> isCompleteHandler(UserTaskName userTask) {
        if (CollectorUtils.isEmpty(COMPLETE_HANDLER_MAP)) {
            synchronized (COMPLETE_HANDLER_MAP) {
                if (CollectorUtils.isEmpty(COMPLETE_HANDLER_MAP)) {
                    COMPLETE_HANDLER_MAP.put(UserTaskName.NEW_PEOPLE, t -> isNewPeopleComplete(t));
                    COMPLETE_HANDLER_MAP.put(UserTaskName.MEET, t -> isMeetComplete(t));
                    COMPLETE_HANDLER_MAP.put(UserTaskName.PERSONAL_INFO, t -> isPersonalInfoComplete(t));
                    COMPLETE_HANDLER_MAP.put(UserTaskName.INVITED, t -> isInvitedComplete(t));
                }
            }
        }
        Function<UserToken, Boolean> function = COMPLETE_HANDLER_MAP.get(userTask);
        if (Objects.isNull(function)) {
            return t -> false;
        }
        return function;
    }

    private boolean isNewPeopleComplete(UserToken userTokenBo) {
        //Todo: 新人引导暂未实现


        return false;
    }

    private boolean isMeetComplete(UserToken userTokenBo) {
        //Todo: 邀请见面暂未实现



        return false;
    }

    private boolean isPersonalInfoComplete(UserToken userTokenBo) {
        UserInfo userInfo = userInfoCached.getByUuid(userTokenBo.getUuid());
        UserMoreInfo userMoreInfo = userMoreInfoCached.getByUuid(userTokenBo.getUuid());
        return Objects.nonNull(userInfo) && Objects.nonNull(userMoreInfo);
    }

    private boolean isInvitedComplete(UserToken userTokenBo) {
        return CollectorUtils.isNotEmpty(userInviteCached.listByUuid(userTokenBo.getUuid()));
    }
}
