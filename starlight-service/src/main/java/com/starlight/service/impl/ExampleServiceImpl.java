package com.starlight.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.starlight.mapper.ExampleMapper;
import com.starlight.model.Example;
import com.starlight.service.ExampleService;
import org.springframework.stereotype.Component;

@Component
public class ExampleServiceImpl extends ServiceImpl<ExampleMapper, Example> implements ExampleService {

}
