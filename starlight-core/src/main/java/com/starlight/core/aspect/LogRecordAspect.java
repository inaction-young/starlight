package com.starlight.core.aspect;

import com.starlight.core.annotation.LogRecord;
import com.starlight.core.enums.LogRecordType;
import com.starlight.core.utils.DateUtils;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 *
 * @ClassName: LogRecordAspect
 * @Description:
 * @Author: Tj
 * @Date: 2023/11/6 下午1:17
 * @Version V1.0.0
 */
@Log4j2
@Aspect
@Component
public class LogRecordAspect {
    @Around("@annotation(com.starlight.core.annotation.LogRecord)")
    public Object logRecord(ProceedingJoinPoint point) throws Throwable {
        Long start = DateUtils.time();

        Object result = null;
        Method method = null;
        Exception exception = null;
        try {
            result = point.proceed();
            method = ((MethodSignature) point.getSignature()).getMethod();
            //返回被监控的方法
            return result;
        } catch (Exception e) {
            throw exception = e;
        } finally {
            Long takingTime = DateUtils.time() - start;
            //不论被监控的方法是否发生异常都需要记录
            log(result, method, takingTime, exception);
            //所有处理完成 清楚线程
//            HttpThreadLocal.clearAll();
            log.info("method: {}/{}.{} taking time:{}ms", new Object[] { method.getDeclaringClass().getName(),
                    method.getDeclaringClass().getSimpleName(), method.getName(), takingTime });
        }
    }

    private void log(Object result, Method method, Long start, Exception exception) {
        //获取注解进来的参数
        LogRecord point = method.getAnnotation(LogRecord.class);
        if (point != null) {
            LogRecordType[] logRecordTypes = point.logRecordType();

            for (LogRecordType logRecordType : logRecordTypes) {
                //对应的类型做对应的日志操作记录
                switch (logRecordType) {
                    case NOTIFY :
                        //短信/邮箱通知

                        break;
                    case OPERATION_RECORD :
                        //持久化

                        break;
                    case BIZ:
                        //业务消费

                        break;
                }
            }
        }
    }
}
