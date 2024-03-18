package com.starlight.core.utils;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.starlight.core.enums.ApiCode;
import com.starlight.core.exception.ApiException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Objects;

/**
 * @ClassName: HttpClientUtils
 * @Description:
 * @Author: Tj
 * @Date: 2023/11/14 下午3:03
 * @Version V1.0
 */
@Log4j2
public class HttpClientUtils {

    private static RestTemplate restTemplate;

    private static RestTemplate getRedisTemplate() {
        if (Objects.isNull(restTemplate)) {
            restTemplate = SpringUtils.getBean(RestTemplate.class);
        }
        return restTemplate;
    }

    public static JSONObject post(String url, Object params) {
        return post(url, params, Maps.newHashMap());
    }

    public static JSONObject post(String url, Object params, Map<String, Object> heads) {
        return post(url, params, JSONObject.class, heads);
    }

    public static  <T> T post(String url, Object params, Class<T> resultClz) {
        return post(url, params, resultClz, Maps.newHashMap());
    }

    public static  <T> T post(String url, Object params, Class<T> resultClz, Map<String, Object> heads) {
        HttpEntity requestEntity;
        if (Objects.nonNull(heads) && !heads.isEmpty()) {
            HttpHeaders headers = new HttpHeaders();
            heads.forEach((k, v) -> headers.set(k, v.toString()));
            requestEntity = new HttpEntity(params, headers);
        } else {
            requestEntity = new HttpEntity(params);
        }

        ResponseEntity<T> responseEntity = getRedisTemplate().postForEntity(url, requestEntity, resultClz);

        if (HttpStatus.OK != responseEntity.getStatusCode()) {
            log.error("[HttpClient RestTemplate] 外部接口调用失败！response -> {}", JSONObject.toJSONString(responseEntity));
            throw new ApiException(ApiCode.API_EXCEPTION);
        }
        return responseEntity.getBody();
    }

}
