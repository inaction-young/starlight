package com.starlight.entity.bo.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.starlight.enums.user.Matrimony;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class UserMoreInfoBo {

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
     * 创建时间
     */
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    private LocalDateTime updatedAt;


}
