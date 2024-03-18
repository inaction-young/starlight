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

    private Long uuid;
    private String element;
    private UserElementType type;
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
