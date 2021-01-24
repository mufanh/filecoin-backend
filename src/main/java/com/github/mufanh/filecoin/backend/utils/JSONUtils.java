package com.github.mufanh.filecoin.backend.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * @author xinquan.huangxq
 */
public class JSONUtils {

    private JSONUtils() {
        throw new AssertionError("Cannot be instantiated");
    }

    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }

    public static String map2Json(Map<String, Object> map) {
        if (map == null) {
            throw new IllegalArgumentException("The map is null.");
        }
        try {
            return mapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    public static Map<String, Object> json2Map(String json) {
        if (StringUtils.isBlank(json)) {
            throw new IllegalArgumentException("The json is null.");
        }
        try {
            return mapper.readValue(json, new TypeReference<Map<String, Object>>() {
            });
        } catch (JsonProcessingException e) {
            return null;
        }
    }


    public static <T> T json2Object(String json, Class<T> valueType) {
        if (StringUtils.isBlank(json)) {
            throw new IllegalArgumentException("The json is null.");
        }

        if (valueType == null) {
            throw new IllegalArgumentException("The value type is null.");
        }

        try {
            return mapper.readValue(json, valueType);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    public static String object2json(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("The obj is null.");
        }
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}
