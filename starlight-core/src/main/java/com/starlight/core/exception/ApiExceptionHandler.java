package com.starlight.core.exception;

import com.starlight.core.enums.ApiCode;
import com.starlight.core.entity.resp.ApiResp;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//Spring的ExceptionHandler, 暂留
@Log4j2
@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ApiResp apiExceptionHandler(ApiException e) {
        log.error("api exception :", e);
        return ApiResp.fail(e.getApiCode());
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ApiResp catchHttpMessageNotReadableException(Exception e) {
        log.error("params exception :", e);
        return ApiResp.fail(ApiCode.API_ILLEGAL_DEFECT_PARAMS);
    }

    @ExceptionHandler(value = Exception.class)
    public ApiResp exceptionHandler(Exception e) {
        log.error("system exception :", e);
        return ApiResp.fail(ApiCode.API_EXCEPTION);
    }


}
