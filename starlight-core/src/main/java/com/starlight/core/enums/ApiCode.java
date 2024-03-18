package com.starlight.core.enums;

import com.google.common.collect.Lists;
import lombok.Getter;

import java.util.List;
import java.util.Objects;

/**
 *
 * @ClassName: ApiCode
 * @Description:
 * @Author: Tj
 * @Date: 2023/11/6 下午3:11
 * @Version V1.0.0
 */
@Getter
public enum ApiCode {
    /** 响应码，仅成功/失败 */
    REP_SUCCESS(0, "成功"),
    REP_FAIL(-1, "失败"),

    /** API 业务处理返回码 */
    /** 成功 */
    API_SUCCESS(0, "成功"),

    /** 1*** 错误码为参数错误 */
    API_ILLEGAL_PARAMS(1000, "非法的参数"),
    API_DEFECT_PARAMS(1001, "缺少必要参数"),
    API_ILLEGAL_DEFECT_PARAMS(1002, "缺少必要参数或非法参数"),

    /** 4*** 错误码为无权限访问错误 */
    API_NOT_USER_TK(4000, "用户未登录"),
    API_USER_TK_EXPIRE(4001, "TK已失效"),
    API_USER_LOGIN_FAIL(4002, "用户登录失败"),

    /** 9*** 错误码为系统异常错误 */
    API_EXCEPTION(9000, "内部系统异常"),
    API_INVITE_EXPIRE(9001, "邀请失效"),
    API_TASK_NOT_COMPLETE(9002, "任务未完成"),




    /** 对外系统错误码映射 */

    /** 对外系统错误码映射 */

    ;

    ApiCode(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    private int code;

    private String msg;


    private static final List<ApiCode> API_CODE_LIST = Lists.newArrayList();
    static {
        ApiCode[] values = ApiCode.values();
        for (ApiCode value : values) {
            API_CODE_LIST.add(value);
        }
    }

    public static ApiCode getByCode(int code) {
        ApiCode apiCode = API_CODE_LIST.get(code);
        if (Objects.isNull(API_CODE_LIST)) {
            return ApiCode.API_EXCEPTION;
        }
        return apiCode;
    }
}