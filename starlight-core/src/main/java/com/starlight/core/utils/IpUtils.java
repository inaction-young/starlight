package com.starlight.core.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @ClassName: IpUtils
 * @Description:
 * @Author: Tj
 * @Date: 2023/7/6 下午5:43
 * @Version V1.0
 */
public class IpUtils {

    public static String getIp() {
        HttpServletRequest request = HttpRequestUtil.getRequest();
        if (Objects.isNull(request)) {
            return StringUtils.EMPTY;
        }
        return getIp(request);
    }

    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if(StringUtils.notBlank(ip) && !"unKnown".equalsIgnoreCase(ip)){
            if (!ip.contains(",")) {
                return ip;
            }
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            String[] ips = ip.split(",");
            return ips[0];
        }

        ip = request.getHeader("X-Real-IP");
        if(StringUtils.notBlank(ip) && !"unKnown".equalsIgnoreCase(ip)){
            return ip;
        }

        return request.getRemoteAddr();
    }
}
