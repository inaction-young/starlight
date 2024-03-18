package com.starlight.manage;

import com.starlight.entity.bo.user.UserAuthBo;
import com.starlight.entity.bo.user.UserTokenBo;

/**
 * @ClassName: UserTokenManage
 * @Description:
 * @Author: Tj
 * @Date: 2023/11/14 下午1:39
 * @Version V1.0
 */
public interface UserTokenManage {

    UserTokenBo createToken(UserAuthBo userAuth);

}
