package com.starlight.core.constants;

/**
 *
 * @ClassName: SystemConstant
 * @Description:
 * @Author: Tj
 * @Date: 2023/11/6 下午3:11
 * @Version V1.0.0
 */
public class SystemConstants {

    public static final String SYS_VERSION = "1.0.0";

    public static final int API_PORT = 8080;

    /**
     * SnowFlake唯一ID生成器配置 ip、hostname 默认值 ip
     * 若配置hostname 则（hostname规则： xxxx.number 如 quickapp.001）
     */
    public final static String KEY_SNOW_MID_GENERATOR = "snow.machine.id.generator";
}
