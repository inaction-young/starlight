package com.starlight.api.controller;

import com.starlight.api.vo.req.user.UserInfoReq;
import com.starlight.api.vo.req.user.UserMoreInfoReq;
import com.starlight.api.vo.req.user.UserReq;
import com.starlight.api.vo.resp.UserResp;
import com.starlight.core.enums.ApiCode;
import com.starlight.core.utils.EntityUtils;
import com.starlight.core.entity.bo.UserToken;
import com.starlight.core.entity.resp.ApiResp;
import com.starlight.core.web.BaseController;
import com.starlight.entity.bo.user.UserBo;
import com.starlight.entity.bo.user.UserInfoBo;
import com.starlight.entity.bo.user.UserMoreInfoBo;
import com.starlight.enums.user.UserTaskName;
import com.starlight.manage.UserInfoManage;
import com.starlight.manage.UserTaskManage;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * 用户信息相关接口
 * @ClassName: UserInfoController
 * @Description:
 * @Author: Tj
 * @Date: 2023/11/7 下午4:54
 * @Version V1.0
 */
@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserInfoController extends BaseController {

    private final UserInfoManage userInfoManage;

    private final UserTaskManage userTaskManage;

    /**
     * 编辑用户信息
     * @param userReq
     * @return
     */
    @PostMapping("/edit")
    public ApiResp<Boolean> edit(@RequestBody UserReq userReq) {
        UserToken userToken = userToken();
        if (Objects.isNull(userReq.getUserInfo()) || Objects.isNull(userReq.getUserMoreInfo())) {
            return ApiResp.fail(ApiCode.API_DEFECT_PARAMS);
        }

        UserInfoBo userInfoBo = EntityUtils.castTo(userReq.getUserInfo(), UserInfoBo.class);
        userInfoBo.setUuid(userToken.getUuid());

        UserMoreInfoBo userMoreInfoBo = EntityUtils.castTo(userReq.getUserMoreInfo(), UserMoreInfoBo.class);
        userMoreInfoBo.setUuid(userToken.getUuid());

        userInfoManage.edit(new UserBo(userInfoBo, userMoreInfoBo));
        userTaskManage.complete(userToken, UserTaskName.PERSONAL_INFO);
        return ApiResp.success(true);
    }

    /**
     * 编辑用户基础信息
     * @param userInfoReq
     * @return
     */
    @PostMapping("/edit/info")
    public ApiResp<Boolean> editUserInfo(@RequestBody UserInfoReq userInfoReq) {
        UserToken userToken = userToken();
        UserInfoBo userInfoBo = EntityUtils.castTo(userInfoReq, UserInfoBo.class);
        userInfoBo.setUuid(userToken.getUuid());
        userInfoManage.editUserInfo(userInfoBo);
        userTaskManage.complete(userToken, UserTaskName.PERSONAL_INFO);
        return ApiResp.success(true);
    }

    /**
     * 编辑用户更多信息
     * @param userMoreInfoReq
     * @return
     */
    @PostMapping("/edit/more")
    public ApiResp<Boolean> editUserMoreInfo(@RequestBody UserMoreInfoReq userMoreInfoReq) {
        UserToken userToken = userToken();
        UserMoreInfoBo userMoreInfoBo = EntityUtils.castTo(userMoreInfoReq, UserMoreInfoBo.class);
        userMoreInfoBo.setUuid(userToken.getUuid());
        userInfoManage.editUserMoreInfo(userMoreInfoBo);
        userTaskManage.complete(userToken, UserTaskName.PERSONAL_INFO);
        return ApiResp.success(true);
    }

    /**
     * 查询用户信息
     * @return
     */
    @PostMapping("/info")
    public ApiResp<UserResp> info() {
        UserBo userBo = userInfoManage.getByUuid(userToken().getUuid());
        return ApiResp.success(EntityUtils.castTo(userBo, UserResp.class));
    }



}
