package com.starlight.api.controller;

import com.starlight.api.vo.req.ExampleReq;
import com.starlight.api.vo.resp.ExampleResp;
import com.starlight.core.utils.CollectorUtils;
import com.starlight.core.utils.EntityUtils;
import com.starlight.core.entity.resp.ApiResp;
import com.starlight.entity.bo.ExampleBo;
import com.starlight.manage.ExampleManage;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 例子
 * @ClassName: ExampleController
 * @Description:
 * @Author: Tj
 * @Date: 2023/11/10 下午2:51
 * @Version V1.0
 */
@Log4j2
@RestController
@RequestMapping("/example")
@RequiredArgsConstructor
public class ExampleController {

    private final ExampleManage exampleManage;

    @PostMapping("/e")
    public ApiResp<List<ExampleResp>> example(@RequestBody ExampleReq req) {
        ExampleBo exampleBo = EntityUtils.castTo(req, ExampleBo.class);
        List<ExampleBo> example = exampleManage.example(exampleBo);

        return ApiResp.success(CollectorUtils
                .map(example, e -> EntityUtils.castTo(e, ExampleResp.class)));
    }

}
