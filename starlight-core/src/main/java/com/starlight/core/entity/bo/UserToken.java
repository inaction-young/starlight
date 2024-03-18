package com.starlight.core.entity.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: UserTokenBo
 * @Description:
 * @Author: Tj
 * @Date: 2023/11/7 下午5:38
 * @Version V1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserToken {

    private String tk;

    private Long uuid;

}
