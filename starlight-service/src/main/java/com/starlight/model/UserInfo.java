package com.starlight.model;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.starlight.enums.user.Gender;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author Tj
 * @since 2023-11-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserInfo implements Serializable {

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
