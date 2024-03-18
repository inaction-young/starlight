package com.starlight.cached;

import com.google.common.collect.Lists;
import com.starlight.core.constants.CachedKeys;
import com.starlight.enums.user.UserInviteStatus;
import com.starlight.model.UserInvite;
import com.starlight.service.UserInviteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName: UserInviteCached
 * @Description:
 * @Author: Tj
 * @Date: 2023/11/10 下午3:44
 * @Version V1.0
 */
@Log4j2
@Component
@RequiredArgsConstructor
public class UserInviteCached {

    private final UserInviteService userInviteService;

    @Cacheable(value = CachedKeys.USER_INVITE, key = "#p0")
    public List<UserInvite> listByUuid(long uuid) {
        return userInviteService.lambdaQuery()
                .eq(UserInvite::getUuid, uuid)
                .in(UserInvite::getStatus, Lists.newArrayList(UserInviteStatus.ACTIVATE, UserInviteStatus.PRIZE))
                .list();
    }

}
