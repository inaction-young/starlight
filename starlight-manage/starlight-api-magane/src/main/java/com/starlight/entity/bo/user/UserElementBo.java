package com.starlight.entity.bo.user;

import com.starlight.enums.user.UserElementType;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @ClassName: UserElementBo
 * @Description:
 * @Author: Tj
 * @Date: 2023/11/7 下午3:32
 * @Version V1.0
 */
@Data
public class UserElementBo {

    /**
     * 用户uuid
     */
    private Long uuid;

    /**
     * 要素
     */
    private String element;

    /**
     * 要素类型，MOBILE-手机，ACCOUNT-账号，WE_CHAT-微信，APPLE_ID-苹果ID
     */
    private UserElementType type;

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
