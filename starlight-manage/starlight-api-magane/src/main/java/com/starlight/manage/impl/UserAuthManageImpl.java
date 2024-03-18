package com.starlight.manage.impl;

import com.starlight.cached.UserAuthCached;
import com.starlight.core.exception.ApiException;
import com.starlight.core.utils.EntityUtils;
import com.starlight.core.utils.SnowflakeIdUtils;
import com.starlight.entity.bo.user.UserAuthBo;
import com.starlight.entity.bo.user.UserElementBo;
import com.starlight.manage.UserAuthManage;
import com.starlight.manage.UserInviteManage;
import com.starlight.model.UserAuth;
import com.starlight.model.UserElement;
import com.starlight.service.UserElementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * @ClassName: UserAuthManageImpl
 * @Description:
 * @Author: Tj
 * @Date: 2023/11/7 下午3:22
 * @Version V1.0
 */
@Log4j2
@Component
@RequiredArgsConstructor
public class UserAuthManageImpl implements UserAuthManage {

    private final UserAuthCached userAuthCached;

    private final UserElementService userElementService;

    private final UserInviteManage userInviteManage;

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public UserAuthBo auth(UserElementBo element) {
        UserElement userElement = userElementService.lambdaQuery()
                .eq(UserElement::getElement, element.getElement())
                .eq(UserElement::getType, element.getType())
                .one();

        if (Objects.nonNull(userElement)) {
            UserAuth userAuth = userAuthCached.getByUuid(userElement.getUuid());
            return EntityUtils.castTo(userAuth, UserAuthBo.class);
        }

        try {
            long uuid = SnowflakeIdUtils.grant();
            UserAuth userAuth = userAuthCached.add(UserAuth.newborn(uuid));

            userElement = EntityUtils.castTo(element, UserElement.class);
            userElement.setUuid(userAuth.getUuid());
            if (userElementService.save(userElement)) {
                throw new ApiException("注册失败");
            }

            return EntityUtils.castTo(userAuth, UserAuthBo.class);
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            throw new ApiException(e);
        }
    }

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public UserAuthBo auth(UserElementBo element, String inviteKey) {
        UserAuthBo auth = auth(element);
        userInviteManage.inviteActivate(inviteKey, auth.getUuid());
        return auth;
    }

}
