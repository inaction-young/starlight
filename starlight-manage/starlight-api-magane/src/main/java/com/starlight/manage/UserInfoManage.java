package com.starlight.manage;

import com.starlight.entity.bo.user.UserBo;
import com.starlight.entity.bo.user.UserInfoBo;
import com.starlight.entity.bo.user.UserMoreInfoBo;

/**
 * @ClassName: UserInfoManage
 * @Description:
 * @Author: Tj
 * @Date: 2023/11/12 下午4:39
 * @Version V1.0
 */
public interface UserInfoManage {

    void edit(UserBo user);

    void editUserInfo(UserInfoBo userInfo);

    void editUserMoreInfo(UserMoreInfoBo userMoreInfo);

    UserBo getByUuid(Long uuid);

}
