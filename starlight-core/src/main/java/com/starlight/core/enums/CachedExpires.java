package com.starlight.core.enums;

import com.starlight.core.constants.CachedKeys;
import com.starlight.core.utils.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @ClassName: CachedKeys
 * @Description:
 * @Author: Tj
 * @Date: 2023/11/11 下午1:39
 * @Version V1.0
 */
@Getter
@AllArgsConstructor
public enum CachedExpires {

    EXAMPLE(CachedKeys.EXAMPLE, 10),

    USER_INFO(CachedKeys.USER_INFO,DateUtils.MINUTE_TIME * 10),

    USER_AUTH(CachedKeys.USER_AUTH, DateUtils.DAY_TIME),

    USER_ELEMENT(CachedKeys.USER_ELEMENT, DateUtils.MINUTE_TIME * 10),
    ;

    /**
     * key
     */
    private String key;

    /**
     * 有效期，-1 永久有效，单位：毫秒
     */
    private long expire;

}
