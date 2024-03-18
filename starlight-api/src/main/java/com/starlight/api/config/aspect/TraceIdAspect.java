package com.starlight.api.config.aspect;

import com.starlight.core.utils.HttpRequestUtil;
import com.starlight.core.utils.SnowflakeIdUtils;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.ThreadContext;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * @ClassName: TraceIdAspect
 * @Description:
 * @Author: Tj
 * @Date: 2023/8/31 下午2:55
 * @Version V1.0
 */
@Log4j2
@Aspect
@Component
public class TraceIdAspect {

    private static final String TRACE_ID = "_tid";

    @Pointcut("execution(* com.starlight.api.controller..*.*(..))")
    public void trace() {

    }

    @Around("trace()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        String tid = SnowflakeIdUtils.unique();
        ThreadContext.put(TRACE_ID, tid);
        HttpServletResponse response = HttpRequestUtil.getResponse();
        //如果有http响应，则加入响应头
        if (Objects.nonNull(response)) {
            response.addHeader(TRACE_ID, tid);
        }
        Object result;
        try {
            result = joinPoint.proceed();
        } finally {
            ThreadContext.clearAll();
        }
        return result;
    }
}
