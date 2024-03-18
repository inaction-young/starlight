package com.starlight.core.utils;

import com.google.common.collect.Lists;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/**
 * @ClassName: SpringUtils
 * @Description:
 * @Author: Tj
 * @Date: 2022/4/14 下午4:29
 * @Version V1.0
 */
public class SpringUtils {
    private static ApplicationContext ctx;
    private static final String EMPTY = "";

    public static void setApplicationContext(ApplicationContext context) {
        SpringUtils.ctx = context;
    }

    public static Environment getEnvironment() {
        return null == ctx ? null : ctx.getEnvironment();
    }

    public static <T> T getBean(Class<T> clazz) {
        return ctx.getBean(clazz);
    }

    public static <T> T getBean(String beanName, Class<T> clazz) {
        return ctx.getBean(beanName, clazz);
    }

    public static <T> List<T> getBeans(Class<T> clazz) {
        Map<String, T> beansOfType = ctx.getBeansOfType(clazz);
        if (CollectorUtils.isEmpty(beansOfType)) {
            return Lists.newArrayList();
        }
        return Lists.newArrayList(beansOfType.values());
    }

    @SuppressWarnings("unused")
    private static String CONTEXT_PATH, APP_NAME, APP_PORT;

    /** context path of the application. **/
    public static String contextPath() {
        return ofValue(CONTEXT_PATH, ()-> confValue("server.servlet.context-path"));
    }

    /** 获取应用名 **/
    public static String applicationName() {
        return ofValue(APP_NAME, ()-> confValue("spring.application.name"));
    }

    /** 获取应用端口 **/
    public static String applicationPort() {
        String port = ofValue(APP_PORT, ()-> confValue("server.port"));
        return (null == port || port.trim().length() < 1) ? "0" : port;
    }

    /** msc.response.used **/
    public static boolean mscResponseUsed() {
        return Boolean.TRUE.toString().equals(confValue("msc.response.used"));
    }

    /** 获取应用运行的环境 **/
    public static String applicationEnv() {
        if(null == ctx || null == ctx.getEnvironment()) {
            return EMPTY;
        }
        String[] envList = ctx.getEnvironment().getActiveProfiles();
        if (null != envList && envList.length > 0) {
            return envList[0];
        }
        return EMPTY;
    }

    /** 获取应用环境KEY值 **/
    public static String confValue(String key) {
        if(null == ctx || null == ctx.getEnvironment()) {
            return EMPTY;
        }
        String value = ctx.getEnvironment().getProperty(key);
        return null == value ? EMPTY : value.trim();
    }

    private static String ofValue(String rs, Supplier<String> supplier) {
        if(null == rs || rs.trim().length() < 1) {
            rs = supplier.get();
        }
        return rs;
    }
}
