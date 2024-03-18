package com.starlight.core.exception;


import com.starlight.core.enums.ApiCode;
import lombok.Getter;

/**
 * 系统异常类
 * @ClassName: ApiException
 * @Description:
 * @Author: Tj
 * @Date: 2023/11/6 下午1:17
 * @Version V1.0.0
 */
@Getter
public class ApiException extends RuntimeException {
    private int code;

    public ApiException() {
        super(ApiCode.API_EXCEPTION.getMsg());
        this.code = ApiCode.API_EXCEPTION.getCode();
    }
    public ApiException(Throwable cause) {
        super(cause);
        this.code = ApiCode.API_EXCEPTION.getCode();
    }


    public ApiException(String msg) {
        this(ApiCode.API_EXCEPTION.getCode(), msg);
    }

    public ApiException(ApiCode apiCode) {
        this(apiCode.getCode(), apiCode.getMsg());
    }

    public ApiException(ApiCode apiCode, String msg) {
        this(apiCode.getCode(), msg);
    }

    public ApiException(int code, String msg) {
        super(msg);
        this.code = code;
    }

    public ApiCode getApiCode() {
        return ApiCode.getByCode(code);
    }

}
