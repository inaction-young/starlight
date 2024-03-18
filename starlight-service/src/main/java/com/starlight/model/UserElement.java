package com.starlight.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.starlight.enums.user.UserElementType;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户要素表
 * </p>
 *
 * @author Tj
 * @since 2023-11-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserElement implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

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
