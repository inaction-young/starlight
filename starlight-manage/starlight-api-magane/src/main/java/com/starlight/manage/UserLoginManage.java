package com.starlight.manage;

import com.starlight.entity.bo.login.LoginBo;
import com.starlight.entity.bo.user.UserElementBo;

/**
 * @ClassName: UserTokenManage
 * @Description:
 * @Author: Tj
 * @Date: 2023/11/13 下午5:14
 * @Version V1.0
 */
public interface UserLoginManage {

    UserElementBo login(LoginBo loginBo);

//    UserElementBo otp();
//
//    UserElementBo wechat();
//
//    UserElementBo apple();

}
