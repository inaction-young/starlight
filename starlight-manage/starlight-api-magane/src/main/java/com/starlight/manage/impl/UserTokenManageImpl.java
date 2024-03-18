package com.starlight.manage.impl;

import com.starlight.core.entity.bo.UserToken;
import com.starlight.core.utils.UserTokenUtils;
import com.starlight.entity.bo.user.UserAuthBo;
import com.starlight.entity.bo.user.UserBo;
import com.starlight.entity.bo.user.UserTokenBo;
import com.starlight.manage.UserInfoManage;
import com.starlight.manage.UserTokenManage;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @ClassName: UserTokenManageImpl
 * @Description:
 * @Author: Tj
 * @Date: 2023/11/14 下午1:39
 * @Version V1.0
 */
@Log4j2
@Component
@RequiredArgsConstructor
public class UserTokenManageImpl implements UserTokenManage {

    private final UserInfoManage userInfoManage;

    @Override
    public UserTokenBo createToken(UserAuthBo userAuth) {
        String tk = UUID.randomUUID().toString().replaceAll("-", "");
        UserToken userToken = new UserToken(tk, userAuth.getUuid());
        //种下登录态
        UserTokenUtils.setUserToCache(userToken);
        UserBo user = userInfoManage.getByUuid(userAuth.getUuid());
        return new UserTokenBo(tk, userAuth.getUuid(), user);
    }

}
