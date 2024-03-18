package com.starlight.core.enums;

import java.lang.reflect.Field;

/**
 * @ClassName: IEnum
 * @Description:
 * @Author: Tj
 * @Date: 2023/7/6 下午2:45
 * @Version V1.0
 */
public interface IEnum {
    /**
     * 替换name属性的值
     * from gitee: https://gitee.com/CodingMonkeys/
     * @param enumT
     * @param value
     * @param <T>
     */
    default  <T extends Enum<T>> void changeNameTo(T enumT, String value) {
        try {
            Field field = enumT.getClass().getSuperclass().getDeclaredField("name");
            field.setAccessible(true);
            field.set(enumT, value);
            field.setAccessible(false);
        } catch (Exception e) {
            throw new RuntimeException("enum " + enumT + "change name to " + value + "error...", e);
        }
    }
}
