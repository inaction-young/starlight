package com.starlight.manage.impl;

import com.starlight.core.utils.CollectorUtils;
import com.starlight.core.utils.EntityUtils;
import com.starlight.entity.bo.ExampleBo;
import com.starlight.manage.ExampleManage;
import com.starlight.model.Example;
import com.starlight.service.ExampleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: EcampleManageImpl
 * @Description:
 * @Author: Tj
 * @Date: 2023/11/2 下午2:53
 * @Version V1.0
 */
@Log4j2
@Service
@RequiredArgsConstructor
public class EcampleManageImpl implements ExampleManage {

    private final ExampleService exampleService;

    @Override
    public List<ExampleBo> example(ExampleBo arg) {
        Example example = EntityUtils.castTo(arg, Example.class);
        exampleService.save(example);
        List<Example> list = exampleService.list();
        return CollectorUtils.map(list, e -> EntityUtils.castTo(e, ExampleBo.class));
    }
}
