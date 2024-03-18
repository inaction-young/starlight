package com.starlight.service.impl;

import com.starlight.model.UserInfo;
import com.starlight.mapper.UserInfoMapper;
import com.starlight.service.UserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author Tj
 * @since 2023-11-07
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

}
