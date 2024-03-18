package com.starlight.core.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.NameFilter;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Set;

/** JSON工具类 **/
public final class JsonUtils {
    public static NameFilter NAME_2_CAMEL = new CamelNameFilter(), NAME_2_UNDERLINE = new UnderlineNameFilter();
    private JsonUtils() {
    }

    public static SerializerFeature[] serializerFeatures(SerializerFeature...features) {
        Set<SerializerFeature> fSet = Sets.newHashSet(
                SerializerFeature.QuoteFieldNames,
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.SkipTransientField,
                SerializerFeature.WriteNullListAsEmpty,
                SerializerFeature.WriteNullNumberAsZero,
                SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.WriteDateUseDateFormat,
                SerializerFeature.WriteNullBooleanAsFalse,
                SerializerFeature.DisableCircularReferenceDetect
                                                     );
        if(!CollectorUtils.isEmpty(features)) {
            for(SerializerFeature feature: features) {
                fSet.add(feature);
            }
        }
        return fSet.toArray(new SerializerFeature[fSet.size()]);
    }

    public static String toJSONString(final Object o, SerializerFeature... features) {
        return null == o ? null : JSON.toJSONString(o, features);
    }

    public static String toJSONString(final Object o, SerializeFilter filter, SerializerFeature... features) {
        return null == o ? null : JSON.toJSONString(o, filter, features);
    }

    public static <T> T parseObject(final byte[] body, final Type type, Feature... features) {
        return null == body ? null : JSON.parseObject(body, type, features);
    }

    public static <T> T parseObject(String body, final Type type, Feature... features) {
        return null == body ? null : JSON.parseObject(body, type, features);
    }
    public static <T> T parseObject(InputStream stream, final Type type, Feature... features) {
        try {
            return null == stream ? null : JSON.parseObject(stream, BytesUtils.UTF8, type, features);
        } catch (Exception e) {
            throw new RuntimeException("JSON parse InputStream to object error ", e);
        }
    }
    public static JSONArray parseArray(String body) {
        return JSON.parseArray((null == body || "".equals(body)) ? "[]" : body);
    }
    public static <T> List<T> parseArray(String body, final Class<T> clazz) {
        return blankBody(body) ? Lists.newArrayList() : JSON.parseArray(body, clazz);
    }
    public static <T> List<T> parseArray(InputStream stream, final Class<T> clazz) {
        return parseArray(BytesUtils.string(stream), clazz);
    }

    public static <T> byte[] toJSONBytes(final T data, SerializerFeature... features) {
        return null == data ? new byte[0] : JSON.toJSONBytes(data, features);
    }

    public static JSONObject toJSON(final Object source) {
        return (JSONObject) JSON.toJSON(source);
    }

    public static JSONObject parseObject(String body) {
        return blankBody(body) ? new JSONObject() : JSON.parseObject(body);
    }

    public static String toUnderlineJson(final Object o, SerializerFeature... features) {
        return toJSONString(o, NAME_2_UNDERLINE, serializerFeatures(features));
    }
    public static <T> T underlineJson2Object(String body, final Type type) {
        if(blankBody(body)) {
            return String.class.equals(type) ? (T)StringUtils.EMPTY : null;
        }
        String camelBody = toJSONString(parseObject(body), NAME_2_CAMEL);
        return parseObject(camelBody, type);
    }

    private static boolean blankBody(String body) {
        return null == body || "".equals(body);
    }

    public static final class UnderlineNameFilter implements NameFilter {
        @Override
        public String process(Object object, String name, Object value) {
            return StringUtils.camel2Underline(name);
        }
    }
    public static final class CamelNameFilter implements NameFilter {
        @Override
        public String process(Object object, String name, Object value) {
            return StringUtils.underline2camel(name);
        }
    }
}
