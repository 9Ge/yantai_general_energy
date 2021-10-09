package com.enercomn.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ObjectMapperUtil {
    private static Logger logger = LoggerFactory.getLogger(ObjectMapperUtil.class);
    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        //序列化的时候序列对象的所有属性
        objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);

        //反序列化的时候如果多了其他属性,不抛出异常
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        //如果是空对象的时候,不抛异常
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        //取消时间的转化格式,默认是时间戳,可以取消,同时需要设置要表现的时间格式
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
//        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * String 转换成 T 对象
     *
     * @param data
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T convertTo(String data, Class<T> clazz) {
        try {
            return objectMapper.readValue(data, clazz);
        } catch (IOException e) {
            logger.error("### JSON字符串：{} 转换 对象：{} 发生异常！", data, clazz, e);
        }
        return null;
    }

    /**
     * String 转换成 T 对象
     *
     * @param data
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T convertTo(String data, Class<T> clazz, String dateFormat) {
        try {
            objectMapper.setDateFormat(new SimpleDateFormat(dateFormat));
            return objectMapper.readValue(data, clazz);
        } catch (IOException e) {
            logger.error("### JSON字符串：{} 转换 对象：{} 发生异常！", data, clazz, e);
        }
        return null;
    }

    /**
     * String 转换成List 集合对象
     *
     * @param data
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> convertToList(String data, Class<T> clazz) {
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(ArrayList.class, clazz);
        try {
            return objectMapper.readValue(data, javaType);
        } catch (IOException e) {
            logger.error("### JSON字符串：{} 转换 对象列表：{} 发生异常！", data, clazz, e);
        }
        return null;
    }

    public static String writeValueAsString(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            logger.error("### 对象：{} 转换  JSON字符串发生异常！", obj, e);
        }
        return null;
    }

    /**
     * 对象转Map
     *
     * @param obj
     * @return
     */
    public static Map<?, ?> objectToMap(Object obj) {
        if (Objects.isNull(obj)) {
            return null;
        }
        return objectMapper.convertValue(obj, LinkedHashMap.class);
    }


    /**
     * 对象转Map
     *
     * @param obj
     * @return
     */
    public static <T> T mapToBean(Map obj, Class<T> clazz) {
        if (Objects.isNull(obj)) {
            return null;
        }
        return objectMapper.convertValue(obj, clazz);
    }
}
