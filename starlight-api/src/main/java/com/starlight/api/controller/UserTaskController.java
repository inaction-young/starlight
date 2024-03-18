package com.starlight.api.controller;

import com.google.common.collect.Lists;
import com.starlight.api.vo.req.user.UserTaskRewardReq;
import com.starlight.api.vo.resp.UserTaskCompleteResp;
import com.starlight.core.utils.CollectorUtils;
import com.starlight.core.entity.bo.UserToken;
import com.starlight.core.entity.resp.ApiResp;
import com.starlight.core.web.BaseController;
import com.starlight.entity.bo.user.UserTaskStatusBo;
import com.starlight.manage.UserTaskManage;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 任务相关接口
 * @ClassName: UserTaskController
 * @Description:
 * @Author: Tj
 * @Date: 2023/11/10 下午3:28
 * @Version V1.0
 */
@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/task")
public class UserTaskController extends BaseController {

    private final UserTaskManage userTaskManage;

    @PostMapping("/reward")
    public ApiResp<Boolean> reward(@RequestBody UserTaskRewardReq req) {
        UserToken userTokenBo = userToken();
        return ApiResp.success(userTaskManage.reward(userTokenBo, req.getTask()));
    }

    @PostMapping("/complete/list")
    public ApiResp<List<UserTaskCompleteResp>> completeList() {
        List<UserTaskStatusBo> userTaskStatusBos = userTaskManage.completeList(userToken());
        if (CollectorUtils.isEmpty(userTaskStatusBos)) {
            return ApiResp.success(Lists.newArrayList());
        }

        return ApiResp.success(CollectorUtils.map(userTaskStatusBos, t -> new UserTaskCompleteResp(t.getTask(), t.getStatus())));
    }

}
