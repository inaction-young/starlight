package com.starlight.service.impl;

import com.starlight.model.UserAuth;
import com.starlight.mapper.UserAuthMapper;
import com.starlight.service.UserAuthService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户建档表 服务实现类
 * </p>
 *
 * @author Tj
 * @since 2023-11-07
 */
@Service
public class UserAuthServiceImpl extends ServiceImpl<UserAuthMapper, UserAuth> implements UserAuthService {

}
