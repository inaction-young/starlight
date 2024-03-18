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

    private Long uuid;

    private String password;

    private UserAuthStatus status;

    private Integer isDeleted;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


}
