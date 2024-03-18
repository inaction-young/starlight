package com.starlight.manage.impl;

import com.starlight.cached.UserElementCached;
import com.starlight.core.utils.EntityUtils;
import com.starlight.entity.bo.login.LoginBo;
import com.starlight.entity.bo.user.UserElementBo;
import com.starlight.enums.user.UserElementType;
import com.starlight.invoke.UmengInvoke;
import com.starlight.manage.UserLoginManage;
import com.starlight.model.UserElement;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @ClassName: UserTokenManageImpl
 * @Description:
 * @Author: Tj
 * @Date: 2023/11/13 下午5:15
 * @Version V1.0
 */
@Log4j2
@Component
@RequiredArgsConstructor
public class UserLoginManageImpl implements UserLoginManage {

    private final UmengInvoke umengInvoke;

    private final UserElementCached userElementCached;

    @Override
    public UserElementBo login(LoginBo loginBo) {
        String mobile = umengInvoke.mobileVerify(loginBo.getCode(), loginBo.getVerifyId());

        UserElement userElement = userElementCached.getByElement(mobile, loginBo.getType());
        if (Objects.nonNull(userElement)) {
            return EntityUtils.castTo(userElement, UserElementBo.class);
        }
        UserElementBo userElementBo = new UserElementBo();
        userElementBo.setElement(mobile);
        userElementBo.setType(UserElementType.MOBILE);
        return userElementBo;
    }

}
