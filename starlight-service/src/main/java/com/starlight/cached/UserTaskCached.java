package com.starlight.cached;

import com.starlight.core.constants.CachedKeys;
import com.starlight.enums.user.UserTaskName;
import com.starlight.model.UserTask;
import com.starlight.service.UserTaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName: UserTaskCached
 * @Description:
 * @Author: Tj
 * @Date: 2023/11/10 下午4:28
 * @Version V1.0
 */
@Log4j2
@Component
@RequiredArgsConstructor
public class UserTaskCached {

    private final UserTaskService userTaskService;

    @Cacheable(value = CachedKeys.LIST_USER_TASK, key = "#p0")
    public List<UserTask> listByUuid(Long uuid) {
        return userTaskService.lambdaQuery()
                .eq(UserTask::getUuid, uuid)
                .list();
    }

    @Cacheable(value = CachedKeys.USER_INVITE, key = "#p0 + ':' + #p1.name")
    public UserTask getByTaskName(long uuid, UserTaskName taskName) {
        return userTaskService.lambdaQuery()
                .eq(UserTask::getUuid, uuid)
                .eq(UserTask::getTaskName, taskName)
                .one();
    }

    @CacheEvict(value = CachedKeys.USER_INVITE, key = "#p0 + ':' + #p1.name")
    public boolean updById(UserTask userTask) {
        return userTaskService.updateById(userTask);
    }

}
