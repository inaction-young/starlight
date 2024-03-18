package com.starlight.entity.bo.user;

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
public class UserBo {

    private UserInfoBo userInfo;

    private UserMoreInfoBo userMoreInfo;
}
