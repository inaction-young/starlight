package com.starlight.api.controller;

import com.starlight.core.utils.UserTokenUtils;
import com.starlight.core.entity.resp.ApiResp;
import com.starlight.core.web.BaseController;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登出相关接口
 * @ClassName: LogoutController
 * @Description:
 * @Author: Tj
 * @Date: 2023/11/13 下午5:03
 * @Version V1.0
 */
@Log4j2
@RestController
@RequestMapping("/logout")
@RequiredArgsConstructor
public class LogoutController extends BaseController {

    @PostMapping("/user")
    public ApiResp<Boolean> user() {
        UserTokenUtils.clearUserByCache(userToken().getTk());
        return ApiResp.success(Boolean.TRUE);
    }

}
