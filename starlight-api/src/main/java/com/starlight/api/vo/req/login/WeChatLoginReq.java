package com.starlight.api.vo.req.login;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName: WeChatLogin
 * @Description:
 * @Author: Tj
 * @Date: 2023/11/10 下午6:22
 * @Version V1.0
 */
@Data
public class WeChatLoginReq extends BaseLoginReq {

    @NotBlank
    private String code;

}
