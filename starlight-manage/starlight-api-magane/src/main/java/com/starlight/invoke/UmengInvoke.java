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
        //请求响应体类型，部分 API 可以根据指定的响应类型来返回对应数据格式，建议手动指定此请求头，如果不设置，部分 HTTP 客户端会设置默认值 */*，导致签名错误。
        heads.put("Accept", "application/json");
        heads.put("X-Ca-Version", "1");
        heads.put("X-Ca-Stage", "RELEASE");
        //请求的 阿里云AppKey，通过云市场等渠道购买的 API 默认已经给 APP 授过权，阿里云所有云产品共用一套 AppKey 体系，删除 ApppKey 请谨慎，避免影响到其他已经开通服务的云产品。
        heads.put("X-Ca-Key", umengCfg.getAliAppKey());
        //请求的时间戳，值为当前时间的毫秒数，时间戳有效时间为15分钟。
        heads.put("X-Ca-Timestamp", System.currentTimeMillis());
        //请求唯一标识，15分钟内 AppKey+API+Nonce 不能重复，与时间戳结合使用才能起到防重放作用。
        heads.put("X-Ca-Nonce", SnowflakeIdUtils.unique());
        //Todo: 签名暂未实现
        //参与签名的自定义请求头，服务端将根据此配置读取请求头进行签名，此处设置不包含 Content-Type、Accept、Content-MD5、Date 请求头，这些请求头已经包含在了基础的签名结构中，详情参照请求签名说明文档。
        heads.put("X-Ca-Signature-Headers", "");
        //请求签名,使用方参照签名机制生成。
        heads.put("X-Ca-Signature", "");

        Map<String, Object> params = Maps.newHashMap();
        params.put("token", code);

        JSONObject mobile = invoke(url, params, heads);
        return mobile.getString("mobile");
    }
    
    private JSONObject invoke(String url, Object params, Map<String, Object> heads) {
        log.info("【友盟远程调用] url -> {}, params -> {}, head -> {}", url, params, heads);
        JSONObject result = HttpClientUtils.post(url, params, heads);
        if (!result.getBoolean("success")) {
            log.info("【友盟远程调用] 失败！url -> {}, result -> {}", result);
        }
        return result.getJSONObject("data");
    }
    
}
