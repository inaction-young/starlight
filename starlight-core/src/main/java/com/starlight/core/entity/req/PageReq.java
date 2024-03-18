package com.starlight.core.entity.req;

import lombok.Data;

/**
 * @ClassName: PageReq
 * @Description:
 * @Author: Tj
 * @Date: 2023/7/6 下午6:09
 * @Version V1.0
 */
@Data
public class PageReq extends BaseReq {

    private int page;

    private int pageSize;

}
