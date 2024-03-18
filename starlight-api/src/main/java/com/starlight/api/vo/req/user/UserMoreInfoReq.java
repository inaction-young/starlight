package com.starlight.api.vo.req.user;

import com.starlight.enums.user.Matrimony;
import lombok.Data;

@Data
public class UserMoreInfoReq {

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


}
