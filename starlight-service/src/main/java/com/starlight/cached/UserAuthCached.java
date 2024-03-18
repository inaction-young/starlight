package com.starlight.cached;

import com.starlight.core.constants.CachedKeys;
import com.starlight.model.UserAuth;
import com.starlight.service.UserAuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 * @ClassName: UserAuthCached
 * @Description:
 * @Author: Tj
 * @Date: 2023/11/7 下午3:14
 * @Version V1.0
 */
@Log4j2
@Component
@RequiredArgsConstructor
public class UserAuthCached {

    private final UserAuthService userAuthService;

    @CachePut(value = CachedKeys.USER_AUTH, key = "#p0.uuid")
    public UserAuth add(UserAuth userAuth) {
        userAuthService.save(userAuth);
        return userAuthService.getById(userAuth.getId());
    }

    @Cacheable(value = CachedKeys.USER_AUTH, key = "#p0")
    public UserAuth getByUuid(Long uuid) {
        return userAuthService.lambdaQuery()
                .eq(UserAuth::getUuid, uuid)
                .one();
    }

    @CacheEvict(value = CachedKeys.USER_AUTH, key = "#p0.uuid")
    public boolean updByUuid(UserAuth userAuth) {
        return userAuthService.lambdaUpdate()
                .eq(UserAuth::getUuid, userAuth.getUuid())
                .update(userAuth);
    }

}
