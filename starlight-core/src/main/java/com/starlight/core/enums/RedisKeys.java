package com.starlight.core.enums;

import com.starlight.core.utils.CollectorUtils;
import com.starlight.core.utils.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @ClassName: RedisKeys
 * @Description: 与 CachedExpires 不同，CachedExpires 是使用注解缓存使用。RedisKeys 是灵活调用API使用。
 * @Author: Tj
 * @Date: 2023/11/13 上午11:29
 * @Version V1.0
 */
@Getter
@AllArgsConstructor
public enum  RedisKeys {

    INVITE_KEY("INVITE_KEY", "@@@%s", DateUtils.HOUR_TIME),
    INVITE_LINK("INVITE_LINK", "@@@%s", DateUtils.HOUR_TIME),
    USER_TOKEN("INVITE_LINK", "@@@%s", DateUtils.HOUR_TIME * 2),

    ;

    /**
     * key
     */
    private String key;

    /**
     * 格式
     */
    private String format;

    /**
     * 有效期，-1 永久有效，单位：毫秒
     */
    private long expire;

    public String formatKey(Object... args) {
        StringBuilder sb = new StringBuilder(key);
        if (CollectorUtils.isEmpty(args)) {
            return sb.append(format).toString();
        }

        return sb.append(String.format(format, args)).toString();
    }
}
