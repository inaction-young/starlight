package com.starlight.entity.bo.user;

import com.starlight.enums.user.UserAuthStatus;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @ClassName: UserAuthBo
 * @Description:
 * @Author: Tj
 * @Date: 2023/11/7 下午3:23
 * @Version V1.0
 */
@Data
public class UserAuthBo {

    /**
     * 用户唯一ID
     */
    private Long uuid;

    /**
     * 密码
     */
    private String password;

    /**
     * 状态，NORMAL:正常，LOGOFF:注销
     */
    private UserAuthStatus status;

    /**
     * 是否删除，0否1是
     */
    private Integer isDeleted;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    private LocalDateTime updatedAt;


}
