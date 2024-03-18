package com.starlight.core.entity.resp;

import com.starlight.core.entity.req.BaseReq;
import lombok.Data;

import java.util.List;

/**
 * @ClassName: PageResp
 * @Description:
 * @Author: Tj
 * @Date: 2023/7/6 下午6:10
 * @Version V1.0
 */
@Data
public class PageResp<T> extends BaseReq {

    private int page;

    private int pageSize;

    private int totalCount;

    private List<T> rows;

}
