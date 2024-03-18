package com.starlight.cached;

import com.starlight.core.constants.CachedKeys;
import com.starlight.model.UserMoreInfo;
import com.starlight.service.UserMoreInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 * @ClassName: UserMoreInfoCached
 * @Description:
 * @Author: Tj
 * @Date: 2023/11/11 下午4:30
 * @Version V1.0
 */
@Log4j2
@Component
@RequiredArgsConstructor
public class UserMoreInfoCached {

    private final UserMoreInfoService userMoreInfoService;

    @Cacheable(value = CachedKeys.USER_INFO, key = "#p0")
    public UserMoreInfo getByUuid(Long uuid) {
        return userMoreInfoService.lambdaQuery().eq(UserMoreInfo::getUuid, uuid).one();
    }

    @CacheEvict(value = CachedKeys.USER_INFO, key = "#p0.uuid")
    public boolean updByUuid(UserMoreInfo userMoreInfo) {
        return userMoreInfoService.lambdaUpdate().eq(UserMoreInfo::getUuid, userMoreInfo.getUuid()).update(userMoreInfo);
    }

}
