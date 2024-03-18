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

    /**
     * 一键登录码；一键登录时，第三方认证SDK会给客户端返回一键登录的token。
     */
    @NotBlank
    private String code;

    /**
     * 同code，为第三方认证SDK获取的verifyId (值通过SDK getVerifyId接口获取回传服务端 )
     */
    private String verifyId;

}
