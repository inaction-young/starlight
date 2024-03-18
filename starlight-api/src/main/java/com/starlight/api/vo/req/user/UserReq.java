package com.starlight.api.vo.req.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: UserBo
 * @Description:
 * @Author: Tj
 * @Date: 2023/11/7 下午4:44
 * @Version V1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserReq {

    /**
     * 用户基本信息
     */
    private UserInfoReq userInfo;

    /**
     * 用户更多信息
     */
    private UserMoreInfoReq userMoreInfo;
}
