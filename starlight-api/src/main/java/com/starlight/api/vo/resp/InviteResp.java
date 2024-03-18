package com.starlight.api.vo.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: InviteResp
 * @Description:
 * @Author: Tj
 * @Date: 2023/11/13 上午11:10
 * @Version V1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InviteResp {

    /**
     * 邀请的链接，点击该链接时会302重定向到登录页面，并会拼接参数inviteKey={key}
     * 用户从此链接登录时，前端需要将{key}传给服务端，登录接口的字段为 "inviteKey"
     */
    private String link;

    /**
     * 邀请数量
     */
    private long count;

}
