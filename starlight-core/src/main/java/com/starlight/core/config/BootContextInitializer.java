package com.starlight.core.config;

import com.starlight.core.utils.SpringUtils;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 *
 * @ClassName: BootContextInitializer
 * @Description:
 * @Author: Tj
 * @Date: 2023/11/6 下午3:07
 * @Version V1.0.0
 */
public class BootContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    @Override
    public void initialize(ConfigurableApplicationContext context) {
        //from tj
        SpringUtils.setApplicationContext(context);
    }
}
