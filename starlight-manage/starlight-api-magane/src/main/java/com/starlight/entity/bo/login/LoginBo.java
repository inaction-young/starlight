package com.starlight.entity.bo.login;

import com.starlight.enums.user.UserElementType;
import lombok.Data;

/**
 * @ClassName: MobileLoginBo
 * @Description:
 * @Author: Tj
 * @Date: 2023/11/14 下午1:46
 * @Version V1.0
 */
@Data
public class LoginBo {

    /**
     * 一键登录码；一键登录时，第三方认证SDK会给客户端返回一键登录的token。
     */
    private String code;

    private String verifyId;

    private UserElementType type;
}
