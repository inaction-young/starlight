package com.starlight.manage;

import com.starlight.core.entity.bo.UserToken;

/**
 * @ClassName: UserInviteManage
 * @Description:
 * @Author: Tj
 * @Date: 2023/11/9 上午11:22
 * @Version V1.0
 */
public interface UserInviteManage {

    String createLink(UserToken userToken);

    String link(String key);

    long countByUuid(long uuid);

    void inviteActivate(String key, long inviteUuid);
}
