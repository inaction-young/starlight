package com.starlight.api.vo.req.user;

import com.starlight.enums.user.Gender;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;


@Data
public class UserInfoReq {

    private String nickname;

    private String fullName;

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


}
