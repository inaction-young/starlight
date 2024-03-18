package com.starlight.api.vo.resp;

import com.starlight.core.utils.EntityUtils;
import com.starlight.entity.bo.user.UserTokenBo;
import lombok.Data;

/**
 * @ClassName: UserTokenResp
 * @Description:
 * @Author: Tj
 * @Date: 2023/11/10 下午6:25
 * @Version V1.0
 */
@Data
public class UserLoginResp {

    /**
     * token
     */
    private String tk;

    /**
     * uuid
     */
    private Long uuid;

    /**
     * 用户信息
     */
    private UserResp userInfo;

    public static UserLoginResp newborn(UserTokenBo token) {
        UserLoginResp userLoginResp = new UserLoginResp();
        userLoginResp.setTk(token.getTk());
        userLoginResp.setUuid(token.getUuid());
        userLoginResp.setUserInfo(EntityUtils.castTo(token.getUserInfo(), UserResp.class));
        return userLoginResp;
    }

}
