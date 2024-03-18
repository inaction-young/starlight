package com.starlight.service.impl;

import com.starlight.model.UserTask;
import com.starlight.mapper.UserTaskMapper;
import com.starlight.service.UserTaskService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户任务表 服务实现类
 * </p>
 *
 * @author Tj
 * @since 2023-11-10
 */
@Service
public class UserTaskServiceImpl extends ServiceImpl<UserTaskMapper, UserTask> implements UserTaskService {

}
