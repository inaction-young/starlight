package com.starlight.api.vo.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: InviteResp
 * @Description:
 * @Author: Tj
 * @Date: 2023/11/13 上午11:10
 * @Version V1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InviteResp {

    private String link;

    /**
     * 邀请数量
     */
    private long count;

}
