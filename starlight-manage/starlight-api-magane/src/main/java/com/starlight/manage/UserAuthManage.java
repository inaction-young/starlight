package com.starlight.manage;

import com.starlight.entity.bo.user.UserAuthBo;
import com.starlight.entity.bo.user.UserElementBo;

/**
 * @ClassName: UserAuthManage
 * @Description:
 * @Author: Tj
 * @Date: 2023/11/7 下午3:21
 * @Version V1.0
 */
public interface UserAuthManage {

    UserAuthBo auth(UserElementBo element);

    UserAuthBo auth(UserElementBo element, String inviteKey);

}
