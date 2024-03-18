package com.starlight.api.vo.req.login;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName: MobileLoginReq
 * @Description:
 * @Author: Tj
 * @Date: 2023/11/10 下午6:21
 * @Version V1.0
 */
@Data
public class MobileLoginReq extends BaseLoginReq {

    @NotBlank
    private String code;

    private String verifyId;

}
