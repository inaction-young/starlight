package com.starlight.core.web;

import com.starlight.core.entity.bo.UserToken;

/**
 * @ClassName: BaseController
 * @Description:
 * @Author: Tj
 * @Date: 2023/11/7 下午5:37
 * @Version V1.0
 */
public class BaseController {

    protected UserToken userToken() {

        return new UserToken();
    }

}
