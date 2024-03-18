package com.starlight.core.utils;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

/** 网络信息工具类 **/
public final class NetworkUtils {
    private static final Logger LOG = LoggerFactory.getLogger(NetworkUtils.class);
    private NetworkUtils() {
    }
    private static final List<InetAddress> LOCAL_IPS = Lists.newArrayList();
    private static final List<InetAddress> INNER_IPS = Lists.newArrayList();
    private static final List<InetAddress> OUTER_IPS = Lists.newArrayList();
    static {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = networkInterfaces.nextElement();
                if(isFictitious(networkInterface.getName())){
                    continue;
                }
                Enumeration<InetAddress> inAddresses = networkInterface.getInetAddresses();
                while (inAddresses.hasMoreElements()) {
                    InetAddress inetAddress = inAddresses.nextElement();
                    if(inetAddress instanceof Inet4Address && !StringUtils.isBlank(inetAddress.getHostAddress())) {
                        //本机的地址，以127开头的IP地址
                        if (inetAddress.isLoopbackAddress()) {
                            LOCAL_IPS.add(inetAddress);
                        }
                        //地区本地地址，内网地址以10，172，192开头的IP地址
                        else if (inetAddress.isSiteLocalAddress() && !inetAddress.isAnyLocalAddress()) {
                            INNER_IPS.add(inetAddress);
                        }
                        //其他地址可认为外网地址
                        if(!inetAddress.isAnyLocalAddress()
                                && !inetAddress.isLoopbackAddress()
                                && !inetAddress.isLinkLocalAddress()
                                && !inetAddress.isSiteLocalAddress()) {
                            OUTER_IPS.add(inetAddress);
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("To find device all address error.....", e);
        }
    }
    @SuppressWarnings("unused") //以网卡顺序排序
    private static String MIN_LB_IP, MAX_LB_IP, MIN_INNER_IP, MAX_INNER_IP, MIN_OUTER_IP, MAX_OUTER_IP;
    /** 最小本地IP **/
    public static String minLocalIp(){
        return machineIP(MIN_LB_IP, () -> CollectorUtils.head(LOCAL_IPS));
    }
    /** 最大本地IP **/
    public static String maxLocalIp(){
        return machineIP(MAX_LB_IP, () -> CollectorUtils.head(LOCAL_IPS));
    }
    /** 最小内网IP **/
    public static String minInnerIp(){
        return machineIP(MIN_INNER_IP, () -> CollectorUtils.head(INNER_IPS));
    }
    /** 最大内网IP **/
    public static String maxInnerIp(){
        return machineIP(MAX_INNER_IP, () -> CollectorUtils.end(INNER_IPS));
    }
    /** 最小外网IP **/
    public static String minOuterIp() {
        return machineIP(MIN_OUTER_IP, () -> CollectorUtils.head(OUTER_IPS));
    }
    /** 最大外网IP **/
    public static String maxOuterIp() {
        return machineIP(MAX_OUTER_IP, () -> CollectorUtils.end(OUTER_IPS));
    }

    /** 获取本机网卡IP **/
    public static String machineIP() {
        String ip = NetworkUtils.maxInnerIp();
        if(StringUtils.isBlank(ip)) {
            ip = NetworkUtils.minInnerIp();
        }
        if(StringUtils.isBlank(ip)) {
            ip = NetworkUtils.maxOuterIp();
        }
        if(StringUtils.isBlank(ip)) {
            ip = NetworkUtils.minOuterIp();
        }
        if(StringUtils.isBlank(ip)) {
            ip = NetworkUtils.maxLocalIp();
        }
        if(StringUtils.isBlank(ip)) {
            ip = NetworkUtils.minLocalIp();
        }
        if(StringUtils.isBlank(ip)) {
            LOG.warn("can not find the machine ip.....");
            return StringUtils.EMPTY;
        }
        return ip;
    }
    /** IPv4转换成十进制数 **/
    public static long ip2long(String ip) {
        String[] p = StringUtils.defaultString(ip).split("\\.");
        if (p.length == 4) {
            int p1 = ((Integer.valueOf(p[0]) << 24) & 0xFF000000);
            int p2 = ((Integer.valueOf(p[1]) << 16) & 0x00FF0000);
            int p3 = ((Integer.valueOf(p[2]) << 8) & 0x0000FF00);
            int p4 = ((Integer.valueOf(p[3]) << 0) & 0x000000FF);
            return ((p1 | p2 | p3 | p4) & 0xFFFFFFFFL);
        }
        return 0;
    }
    /** 十进制数转换成IPv4 **/
    public static String long2ip(long ip) {
        StringBuilder sb = new StringBuilder();
        sb.append((ip >> 24) & 0xFF).append('.')
                .append((ip >> 16) & 0xFF).append('.')
                .append((ip >> 8) & 0xFF).append('.')
                .append((ip >> 0) & 0xFF);
        return sb.toString();
    }
    /** 获取客户端真实IP **/
    public static String ofClientIp(HttpServletRequest request) {
        String fromSource = "X-Forwarded-For";
        String ip = request.getHeader("X-Forwarded-For");
        if (noMappingIp(ip)) {
            fromSource = "Proxy-Client-IP";
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (noMappingIp(ip)) {
            fromSource = "WL-Proxy-Client-IP";
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (noMappingIp(ip)) {
            fromSource = "X-Real-IP";
            ip = request.getHeader("X-Real-IP");
        }
        if (noMappingIp(ip)) {
            ip = request.getRemoteAddr();
            fromSource = "request.getRemoteAddr";
        }
        LOG.debug("GET Client IP: {}, fromSource: {}", ip, fromSource);
        return ip.equals("0:0:0:0:0:0:0:1") ? machineIP() : ip.split(",")[0].trim();
    }
    /** 获取本地 HOST NAME **/
    public static String localHostName() {
        String cn = System.getenv("COMPUTERNAME");
        if (!StringUtils.isBlank(cn)) {
            return cn;
        } else {
            try {
                return (InetAddress.getLocalHost()).getHostName();
            } catch (UnknownHostException uhe) {
                // host = "hostname: hostname"
                String host = uhe.getMessage();
                if (!StringUtils.isBlank(host)) {
                    int colon = host.indexOf(':');
                    if (colon > 0) {
                        return host.substring(0, colon);
                    }
                }
                return "UnknownHost";
            }
        }
    }

    private static boolean noMappingIp(String ip) {return StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip);}

    private static boolean isFictitious(String name) {
        return StringUtils.defaultString(name).startsWith("docker");
    }
    private static String machineIP(String rs, Supplier<Optional<InetAddress>> supplier) {
        if(StringUtils.isBlank(rs)) {
            Optional<InetAddress> itOpt = supplier.get();
            if (itOpt.isPresent()) {
                rs = itOpt.get().getHostAddress();
            }
        }
        return StringUtils.defaultString(rs);
    }
}
