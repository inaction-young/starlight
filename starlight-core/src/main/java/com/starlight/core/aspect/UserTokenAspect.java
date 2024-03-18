package com.starlight.core.aspect;

import com.starlight.core.enums.ApiCode;
import com.starlight.core.utils.HttpRequestUtil;
import com.starlight.core.utils.UserTokenUtils;
import com.starlight.core.entity.bo.UserToken;
import com.starlight.core.entity.resp.ApiResp;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @ClassName: UserTkAspect
 * @Description:
 * @Author: Tj
 * @Date: 2023/7/4 上午10:59
 * @Version V1.0
 */
@Log4j2
@Aspect
@Component
@RequiredArgsConstructor
public class UserTokenAspect {

    /** 以 controller 包下定义的所有请求为切入点 */
    @Pointcut("@annotation(com.starlight.core.annotation.User)")
    public void user() {}

    @Around("user()")
    public Object doAround(ProceedingJoinPoint point) throws Throwable {
        HttpServletRequest request = HttpRequestUtil.getRequest();
        String tk = request.getHeader("TK");
        if (StringUtils.isBlank(tk)) {
            tk = request.getParameter("TK");
        }
        if (StringUtils.isBlank(tk)) {
            return ApiResp.fail(ApiCode.API_NOT_USER_TK);
        }

        UserToken userTokenBo = UserTokenUtils.getUserByCache(tk);
        if (Objects.isNull(userTokenBo) || Objects.isNull(userTokenBo.getUuid())) {
            return ApiResp.fail(ApiCode.API_USER_TK_EXPIRE);
        }

        UserTokenUtils.setUser(userTokenBo);
        Object result = point.proceed();
        UserTokenUtils.clearUser();
        return result;
    }

}
