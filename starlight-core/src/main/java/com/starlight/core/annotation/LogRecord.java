package com.starlight.core.annotation;


import com.starlight.core.enums.LogRecordType;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @ClassName: AppStartController
 * @Description:
 * @Author: Tj
 * @Date: 2023/11/6 下午1:17
 * @Version V1.0.0
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface LogRecord {
    LogRecordType[] logRecordType() default {};
}
