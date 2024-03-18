package com.starlight.core.utils;

import com.starlight.core.enums.RedisKeys;
import com.starlight.core.entity.bo.UserToken;

/**
 * @ClassName: UserTkUtils
 * @Description:
 * @Author: Tj
 * @Date: 2023/7/4 上午11:13
 * @Version V1.0
 */
public class UserTokenUtils {

    private static final ThreadLocal<UserToken> user_account_local = new ThreadLocal();

    public static void setUser(UserToken user) {
        clearUser();
        user_account_local.set(user);
    }

    public static UserToken getUser() {
        return user_account_local.get();
    }

    public static void clearUser() {
        user_account_local.remove();
    }

    public static void setUserToCache(UserToken user, Long... exp) {
        long expire = RedisKeys.USER_TOKEN.getExpire();
        if (CollectorUtils.isNotEmpty(exp)) {
            expire = exp[0];
        }
        RedisUtils.set(RedisKeys.USER_TOKEN.formatKey(user.getTk()), user, expire);
    }

    public static UserToken getUserByCache(String tk) {
        return RedisUtils.get(RedisKeys.USER_TOKEN.formatKey(tk), UserToken.class);
    }

    public static void clearUserByCache(String tk) {
        RedisUtils.del(RedisKeys.USER_TOKEN.formatKey(tk));
    }

}
