package com.starlight.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.starlight.enums.user.Matrimony;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户更多信息表
 * </p>
 *
 * @author Tj
 * @since 2023-11-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserMoreInfo implements Serializable {

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
     * 户籍
     */
    private String householdRegister;

    /**
     * 工作（职业）
     */
    private String job;

    /**
     * 工作经历
     */
    private String career;

    /**
     * 婚姻状况，U-未知，Y-已婚，N-未婚
     */
    private Matrimony matrimony;

    /**
     * 好友评价
     */
    private String friendReviews;

    /**
     * 恋爱经历
     */
    private String loveExp;

    /**
     * 兴趣爱好
     */
    private String interest;

    /**
     * 自我介绍
     */
    private String selfIntroduction;

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
