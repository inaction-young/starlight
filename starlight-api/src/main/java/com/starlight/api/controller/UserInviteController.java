package com.starlight.api.controller;

import com.starlight.api.vo.resp.InviteResp;
import com.starlight.core.utils.HttpRequestUtil;
import com.starlight.core.entity.bo.UserToken;
import com.starlight.core.entity.resp.ApiResp;
import com.starlight.core.web.BaseController;
import com.starlight.enums.user.UserTaskName;
import com.starlight.manage.UserInviteManage;
import com.starlight.manage.UserTaskManage;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * 用户邀请链接相关接口
 * @ClassName: UserInviteController
 * @Description:
 * @Author: Tj
 * @Date: 2023/11/9 上午11:43
 * @Version V1.0
 */
@Log4j2
@Controller
@RequestMapping("/invite")
@RequiredArgsConstructor
public class UserInviteController extends BaseController {

    private final UserInviteManage userInviteManage;

    private final UserTaskManage userTaskManage;

    /**
     * 创建用户的邀请链接
     * @return
     */
    @ResponseBody
    @PostMapping("/create/link")
    public ApiResp<InviteResp> invite() {
        UserToken userTokenBo = userToken();
        String link = userInviteManage.createLink(userTokenBo);
        long count = userInviteManage.countByUuid(userTokenBo.getUuid());
        return ApiResp.success(new InviteResp(link, count));
    }

    /**
     * 邀请链接
     * @return
     */
    @PostMapping("/link/{key}")
    public void link(@PathVariable("key") String key) throws IOException {
        //任务完成埋点
        userTaskManage.complete(userToken(), UserTaskName.INVITED);
        String url = userInviteManage.link(key);
        log.info("Invite 302 url， key —> {}, url -> {}", key, url);
        HttpRequestUtil.getResponse().sendRedirect(url);
    }

}
