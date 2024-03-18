package com.starlight.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.starlight.enums.user.UserAuthStatus;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 用户建档表
 * </p>
 *
 * @author Tj
 * @since 2023-11-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserAuth implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

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


    public static UserAuth newborn(Long uuid) {
        UserAuth userAuth = new UserAuth();
        userAuth.setUuid(uuid);
        userAuth.setStatus(UserAuthStatus.NORMAL);
        return userAuth;
    }
}
