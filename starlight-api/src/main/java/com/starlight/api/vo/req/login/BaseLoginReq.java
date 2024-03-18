package com.starlight.api.vo.req.login;

import lombok.Data;

/**
 * @ClassName: BaseLoginReq
 * @Description:
 * @Author: Tj
 * @Date: 2023/11/14 下午1:53
 * @Version V1.0
 */
@Data
public class BaseLoginReq {

    /**
     * 邀请KEY，如果是从邀请链接进入登录页面，会携带邀请KEY参数inviteKey={key}，调用登录接口时将{key}传至服务端。
     */
    private String inviteKey;

}
