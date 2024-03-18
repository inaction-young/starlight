package com.starlight.entity.bo.user;

import com.starlight.enums.user.Gender;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
public class UserInfoBo {

    /**
     * 用户uuid
     */
    private Long uuid;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 姓名
     */
    private String fullName;

    /**
     * 生日
     */
    private LocalDate birthday;

    /**
     * 性别，U-未知，M-男，F-女
     */
    private Gender gender;

    /**
     * 身高 CM
     */
    private Integer height;

    /**
     * 体重 KG
     */
    private BigDecimal weight;

    /**
     * 身份证
     */
    private String idCard;

    /**
     * 居住地
     */
    private String resideAddress;

    /**
     * 毕业学校
     */
    private String graduationSchool;

    /**
     * 毕业专业
     */
    private String graduationMajor;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    private LocalDateTime updatedAt;


}
