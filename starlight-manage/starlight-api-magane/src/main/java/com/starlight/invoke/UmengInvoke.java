package com.starlight.invoke;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.starlight.core.utils.HttpClientUtils;
import com.starlight.core.utils.SnowflakeIdUtils;
import com.starlight.entity.cfg.UmengCfg;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @ClassName: YouMengInvok
 * @Description:
 * @Author: Tj
 * @Date: 2023/11/14 下午2:51
 * @Version V1.0
 */
@Log4j2
@Component
@RequiredArgsConstructor
public class UmengInvoke {

    private final UmengCfg umengCfg;

    public String mobileVerify(String code, String verifyId) {
        String url = String.format(umengCfg.getMobileUrl(), umengCfg.getAppKey(), verifyId);

        Map<String, Object> heads = Maps.newHashMap();
        heads.put("Accept", "application/json");
        heads.put("X-Ca-Version", "1");
        heads.put("X-Ca-Stage", "RELEASE");
        heads.put("X-Ca-Key", umengCfg.getAliAppKey());
        heads.put("X-Ca-Timestamp", System.currentTimeMillis());
        heads.put("X-Ca-Nonce", SnowflakeIdUtils.unique());
        //Todo: 签名暂未实现
        heads.put("X-Ca-Signature-Headers", "");
        heads.put("X-Ca-Signature", "");

        Map<String, Object> params = Maps.newHashMap();
        params.put("token", code);

        JSONObject mobile = invoke(url, params, heads);
        return mobile.getString("mobile");
    }
    
    private JSONObject invoke(String url, Object params, Map<String, Object> heads) {
        JSONObject result = HttpClientUtils.post(url, params, heads);
        if (!result.getBoolean("success")) {
        }
        return result.getJSONObject("data");
    }
    
}
