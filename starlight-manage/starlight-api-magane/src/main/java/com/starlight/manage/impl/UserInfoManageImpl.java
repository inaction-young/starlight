package com.starlight.manage.impl;

import com.starlight.cached.UserInfoCached;
import com.starlight.cached.UserMoreInfoCached;
import com.starlight.core.exception.ApiException;
import com.starlight.core.utils.EntityUtils;
import com.starlight.entity.bo.user.UserBo;
import com.starlight.entity.bo.user.UserInfoBo;
import com.starlight.entity.bo.user.UserMoreInfoBo;
import com.starlight.manage.UserInfoManage;
import com.starlight.model.UserInfo;
import com.starlight.model.UserMoreInfo;
import com.starlight.service.UserInfoService;
import com.starlight.service.UserMoreInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * @ClassName: UserInfoManageImpl
 * @Description:
 * @Author: Tj
 * @Date: 2023/11/7 下午4:40
 * @Version V1.0
 */
@Log4j2
@Component
@RequiredArgsConstructor
public class UserInfoManageImpl implements UserInfoManage {

    private final UserInfoCached userInfoCached;
    private final UserMoreInfoCached userMoreInfoCached;


    private final UserInfoService userInfoService;
    private final UserMoreInfoService userMoreInfoService;

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public void edit(UserBo user) {
        try {
            editUserInfo(user.getUserInfo());
            editUserMoreInfo(user.getUserMoreInfo());
        } catch (Exception e) {
            throw new ApiException(e);
        }
    }

    @Override
    public void editUserInfo(UserInfoBo userInfo) {
        UserInfo info = userInfoCached.getByUuid(userInfo.getUuid());
        if (Objects.isNull(info)) {
            userInfoService.save(EntityUtils.castTo(userInfo, UserInfo.class));
            return;
        }
        userInfoCached.updByUuid(EntityUtils.castTo(userInfo, UserInfo.class));
    }

    @Override
    public void editUserMoreInfo(UserMoreInfoBo userMoreInfo) {
        UserMoreInfo info = userMoreInfoCached.getByUuid(userMoreInfo.getUuid());
        if (Objects.isNull(info)) {
            userMoreInfoService.save(EntityUtils.castTo(userMoreInfo, UserMoreInfo.class));
            return;
        }
        userMoreInfoCached.updByUuid(EntityUtils.castTo(userMoreInfo, UserMoreInfo.class));
    }

    @Override
    public UserBo getByUuid(Long uuid) {
        UserInfo userInfo = userInfoCached.getByUuid(uuid);
        UserMoreInfo userMoreInfo = userMoreInfoCached.getByUuid(uuid);
        return new UserBo(EntityUtils.castTo(userInfo, UserInfoBo.class), EntityUtils.castTo(userMoreInfo, UserMoreInfoBo.class));
    }
}
