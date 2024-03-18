package com.starlight.entity.bo.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: UserTokenResp
 * @Description:
 * @Author: Tj
 * @Date: 2023/11/10 下午6:25
 * @Version V1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserTokenBo {

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
    private UserBo userInfo;

}
