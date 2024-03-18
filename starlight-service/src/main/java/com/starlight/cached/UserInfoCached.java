package com.starlight.cached;

import com.starlight.core.constants.CachedKeys;
import com.starlight.model.UserInfo;
import com.starlight.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 * @ClassName: UserInfoCached
 * @Description:
 * @Author: Tj
 * @Date: 2023/11/7 下午2:17
 * @Version V1.0
 */
@Log4j2
@Component
@RequiredArgsConstructor
public class UserInfoCached {

    private final UserInfoService userInfoService;

    @Cacheable(value = CachedKeys.USER_INFO, key = "#p0")
    public UserInfo getByUuid(Long uuid) {
        return userInfoService.lambdaQuery().eq(UserInfo::getUuid, uuid).one();
    }

    @CacheEvict(value = CachedKeys.USER_INFO, key = "#p0.uuid")
    public boolean updByUuid(UserInfo userInfo) {
        return userInfoService.lambdaUpdate().eq(UserInfo::getUuid, userInfo.getUuid()).update(userInfo);
    }
}
