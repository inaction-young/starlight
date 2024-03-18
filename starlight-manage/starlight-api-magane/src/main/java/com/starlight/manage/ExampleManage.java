package com.starlight.manage;

import com.starlight.entity.bo.ExampleBo;

import java.util.List;

/**
 * @ClassName: ExampleManage
 * @Description:
 * @Author: Tj
 * @Date: 2023/11/2 下午2:53
 * @Version V1.0
 */
public interface ExampleManage {

    List<ExampleBo> example(ExampleBo arg);

}
