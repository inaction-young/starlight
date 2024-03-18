package com.starlight.core.enums;

import com.google.common.collect.Lists;
import com.starlight.core.constants.SystemConstants;
import lombok.Getter;

import java.util.List;
import java.util.Objects;

/**
 *
 * @ClassName: ServerCode
 * @Description:
 * @Author: Tj
 * @Date: 2023/11/6 下午3:11
 * @Version V1.0.0
 */
@Getter
public enum ServerCode {
    /** 响应码，仅成功/失败 */
    REP_SUCCESS(0, "成功"),
    REP_FAIL(-1, "失败"),
    
    /** 面相内部服务端 API  返回码 */
    /** 所有内部服务错误码都按照 server port + code 的格式, 8000位端口，1001为code */
    /** 1*** 错误码为参数错误 */
    SER_DEFECT_PARAMS(SystemConstants.API_PORT + 80001001, "缺少必要参数参数"),

    /** 2*** 错误码，为请求内部系统错误 */
    SER_MQ_SEND_FAILURE(SystemConstants.API_PORT + 80002001, "MQ 消息发送失败"),

    /** 3*** 错误码, 为请求外部系统错误 */
    SER_WECHAT_REQUEST_FAILURE(SystemConstants.API_PORT + 80003001, "请求微信失败"),

    /** 3*** 错误码, 为系统异常错误  */
    SER_SYSTEM_EXCEPTION(SystemConstants.API_PORT + 80009000, "系统异常"),
    /** 面相内部服务端 API  返回码 */
    
    
    /** 对外系统错误码映射 */
    
    /** 对外系统错误码映射 */

    ;

    ServerCode(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    private int code;

    private String msg;


    private static final List<ServerCode> API_CODE_LIST = Lists.newArrayList();
    static {
        ServerCode[] values = ServerCode.values();
        for (ServerCode value : values) {
            API_CODE_LIST.add(value);
        }
    }

    public static ServerCode getByCode(int code) {
        ServerCode apiCode = API_CODE_LIST.get(code);
        if (Objects.isNull(API_CODE_LIST)) {
            return ServerCode.SER_SYSTEM_EXCEPTION;
        }
        return apiCode;
    }
}