package com.starlight.entity.cfg;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ClassName: UmengCfg
 * @Description:
 * @Author: Tj
 * @Date: 2023/11/14 下午3:33
 * @Version V1.0
 */
@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(prefix = "umeng")
public class UmengCfg {

    private String mobileUrl;

    private String appKey;

    private String aliAppKey;


}
