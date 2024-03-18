package com.starlight.api.controller;

import com.starlight.api.vo.req.login.AppleLoginReq;
import com.starlight.api.vo.req.login.MobileLoginReq;
import com.starlight.api.vo.req.login.OtpLoginReq;
import com.starlight.api.vo.req.login.WeChatLoginReq;
import com.starlight.api.vo.resp.UserLoginResp;
import com.starlight.core.entity.resp.ApiResp;
import com.starlight.core.utils.EntityUtils;
import com.starlight.core.utils.StringUtils;
import com.starlight.core.web.BaseController;
import com.starlight.entity.bo.login.LoginBo;
import com.starlight.entity.bo.user.UserAuthBo;
import com.starlight.entity.bo.user.UserElementBo;
import com.starlight.entity.bo.user.UserTokenBo;
import com.starlight.enums.user.UserElementType;
import com.starlight.manage.UserAuthManage;
import com.starlight.manage.UserLoginManage;
import com.starlight.manage.UserTokenManage;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录相关接口
 * @ClassName: LoginController
 * @Description:
 * @Author: Tj
 * @Date: 2023/11/10 下午5:52
 * @Version V1.0
 */
@Log4j2
@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController extends BaseController {

    private final UserLoginManage userLoginManage;

    private final UserAuthManage userAuthManage;

    private final UserTokenManage userTokenManage;

    /**
     * 手机号一键登录
     * @param req
     * @return
     */
    @PostMapping("/mobile")
    public ApiResp<UserLoginResp> mobile(@RequestBody MobileLoginReq req) {
        LoginBo loginBo = EntityUtils.castTo(req, LoginBo.class);
        loginBo.setType(UserElementType.MOBILE);
        UserElementBo mobileElement = userLoginManage.login(loginBo);
        UserAuthBo userAuth = auth(mobileElement, req.getInviteKey());
        UserTokenBo token = userTokenManage.createToken(userAuth);
        return ApiResp.success(UserLoginResp.newborn(token));
    }

    /**
     * 手机短信登录
     * @param req
     * @return
     */
    @PostMapping("/otp")
    public ApiResp<UserLoginResp> otp(@RequestBody OtpLoginReq req) {
        LoginBo loginBo = EntityUtils.castTo(req, LoginBo.class);
        loginBo.setType(UserElementType.MOBILE);
        UserElementBo mobileElement = userLoginManage.login(loginBo);
        UserAuthBo userAuth = auth(mobileElement, req.getInviteKey());
        UserTokenBo token = userTokenManage.createToken(userAuth);
        return ApiResp.success(UserLoginResp.newborn(token));
    }

    /**
     * 微信登录
     * @param req
     * @return
     */
    @PostMapping("/wechat")
    public ApiResp<UserLoginResp> wechat(@RequestBody WeChatLoginReq req) {
        LoginBo loginBo = EntityUtils.castTo(req, LoginBo.class);
        loginBo.setType(UserElementType.WE_CHAT);
        UserElementBo mobileElement = userLoginManage.login(loginBo);
        UserAuthBo userAuth = auth(mobileElement, req.getInviteKey());
        UserTokenBo token = userTokenManage.createToken(userAuth);
        return ApiResp.success(UserLoginResp.newborn(token));
    }

    /**
     * 苹果ID登录
     * @param req
     * @return
     */
    @PostMapping("/apple")
    public ApiResp<UserLoginResp> apple(@RequestBody AppleLoginReq req) {

        return ApiResp.success(null);
    }

    private UserAuthBo auth(UserElementBo element, String inviteKey) {
        if (StringUtils.isBlank(inviteKey)) {
            return userAuthManage.auth(element);
        }
        return userAuthManage.auth(element, inviteKey);
    }
}
