package com.enercomn.util;

import com.alibaba.fastjson.JSONObject;
import com.enercomn.util.constant.CommonCode;
import com.enercomn.util.constant.RedisCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * redisTemplate封装
 *
 *  @author zjjlive@dist.com.cn
 */
@Slf4j
@Component
@SuppressWarnings({"all"})
public class RedisUtil {

    /**
     * redis key 分隔符
     */
    public final static String sparate = ".";

    @Autowired
    private RedisTemplate redisTemplate;

    public RedisUtil(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 指定缓存失效时间
     * @param key 键
     * @param time 时间(秒)
     * @return
     */
    public boolean expire(String key, long time){
        try {
            if(time>0){
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据key 获取过期时间
     * @param key 键 不能为null
     * @return 时间(秒) 返回0代表为永久有效
     */
    public long getExpire(String key){
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 判断key是否存在
     * @param key 键
     * @return true 存在 false不存在
     */
    public boolean hasKey(String key){
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除缓存
     * @param key 可以传一个值 或多个
     */
    @SuppressWarnings("unchecked")
    public void del(String... key){
        if(key!=null&&key.length>0){
            if(key.length==1){
                redisTemplate.delete(key[0]);
            }else{
                redisTemplate.delete((Collection<String>) CollectionUtils.arrayToList(key));
            }
        }
    }

    /**
     * 删除缓存
     * @param key 可以传一个值 或多个
     */
    @SuppressWarnings("unchecked")
    public Boolean delSystemPoint(String key){
        if(StringUtils.isNotEmpty(key)){
           return redisTemplate.delete(RedisCode.SYSTEM_POINT_BEAN.getCode() + key);
        }else{
            log.error("删除redis缓存异常，传入key为空！");
           return false;
        }
    }

    //============================String=============================
    /**
     * 普通缓存获取
     * @param key 键
     * @return 值
     */
    public Object get(String key){
        try {
//            log.info("key键<<<<<<<<<<<>>>>>>>>>>>>>>>>>："+key);

            return key==null?null:redisTemplate.opsForValue().get(key);

        }catch (Exception e){
            log.error("普通缓存获取！！错误！！key键>>>>>>："+key,e);
            return null;
        }
    }

    //============================String=============================
    /**
     * String普通缓存获取
     * @param key 键
     * @return 值
     */
    public String getString(String key){
        try {
            return key==null?"0":redisTemplate.opsForValue().get(key)==null?"0": StringUtils.getString(redisTemplate.opsForValue().get(key));
        }catch (Exception e){
            log.error("String普通缓存获取！！错误！！key键>>>>>>："+key,e);
            return null;
        }
    }


    /**
     * 获取采集器状态
     * @param key 键
     * @return 值
     */
    public String getCollectStatus(String key){
        try {
            if(StringUtils.isNotEmpty(key)){
                return StringUtils.getString(redisTemplate.opsForValue().get(RedisCode.COLLECTSTATE.getCode()+key));
            }else{
                return null;
            }
        }catch (Exception e){
            log.error("采集器状态缓存获取错误！！key键>>>>>>："+key,e);
            return null;
        }
    }

    /**
     * 获取实时值
     * @param key 键
     * @return 值
     */
    public String getAttrValue(String key){
        try {
            if(StringUtils.isNotEmpty(key)){
                log.info("key:" + RedisCode.ATTR_VALUE.getCode()+key);
                return StringUtils.getString(redisTemplate.opsForValue().get(RedisCode.ATTR_VALUE.getCode()+key));
            }else{
                return null;
            }
        }catch (Exception e){
            log.error("实时值缓存获取错误！！key键>>>>>>："+key,e);
            return null;
        }
    }

    /**
     * 获取当日实时值最小值
     * @param key 键
     * @return 值
     */
    public String getAttrValueMinimun(String key){
        try {
            if(StringUtils.isNotEmpty(key)){
                return StringUtils.getString(redisTemplate.opsForValue().get(RedisCode.ATTR_VALUE_MINIMUN.getCode()+key));
            }else{
                return null;
            }
        }catch (Exception e){
            log.error("当日实时值最小值缓存获取错误！！key键>>>>>>："+key,e);
            return null;
        }
    }

    public String getTableInfo (String cTpId){
        try {
            if(StringUtils.isNotEmpty(cTpId)){
                return StringUtils.getString(redisTemplate.opsForValue().get(RedisCode.TABLE_INFO.getCode()+cTpId));
            }else{
                return null;
            }
        }catch (Exception e){
            log.error("项目表信息缓存获取错误！！key键>>>>>>："+cTpId,e);
            return null;
        }
    }

//    /**
//     * 普通缓存获取
//     * @param key 键
//     * @return 值
//     */
//    public Object jGet(String key){
//        try {
////            log.info("key键<<<<<<<<<<<>>>>>>>>>>>>>>>>>："+key);
//            redisTemplate.setValueSerializer(new FastJsonRedisSerializer(JSONObject.class));
//            return key==null?null:redisTemplate.opsForValue().get(key);
//            redisTemplate.setValueSerializer();
//        }catch (Exception e){
//            log.error("普通缓存获取！！错误！！key键>>>>>>："+key,e);
//            return null;
//        }
//    }

    /**
     * 普通缓存放入
     * @param key 键
     * @param value 值
     * @return true成功 false失败
     */
    public boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            log.error("推送至redis 缓存异常， " ,e);
            return false;
        }
    }

    /**
     * 当日实时值最小值缓存放入
     * @param key 键
     * @param value 值
     * @return true成功 false失败
     */
    public boolean setAttrValueMinimun(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(RedisCode.ATTR_VALUE_MINIMUN.getCode() + key, value);
            return true;
        } catch (Exception e) {
            log.error("推送当日实时值最小值至redis 缓存异常， " ,e);
            return false;
        }
    }

    /**
     * 通道点位信息缓存放入
     * @param key
     * @param value
     * @return
     */
    public boolean setSystemPoint(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(RedisCode.SYSTEM_POINT_BEAN.getCode() + key, value);
            return true;
        } catch (Exception e) {
            log.error("推送通道点位信息至redis 缓存异常， " ,e);
            return false;
        }
    }

    /**
     * 普通缓存放入并设置时间
     * @param key 键
     * @param value 值
     * @param time 时间(秒) time要大于0 如果time小于等于0 将设置无限期
     * @return true成功 false 失败
     */
    public boolean set(String key, Object value, long time){
        try {
            if(time>0){
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            }else{
                set(key, value);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 递增
     * @param key 键
     * @param delta 要增加几(大于0)
     * @return
     */
    public long incr(String key, long delta){
        if(delta<0){
            throw new RuntimeException("递增因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, delta);
    }

    /**
     * 递减
     * @param key 键
     * @param delta 要减少几(小于0)
     * @return
     */
    public long decr(String key, long delta){
        if(delta<0){
            throw new RuntimeException("递减因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, -delta);
    }

    //================================Map=================================
    /**
     * HashGet
     * @param key 键 不能为null
     * @param item 项 不能为null
     * @return 值
     */
    public Object hget(String key, String item){
        return redisTemplate.opsForHash().get(key, item);
    }

    /**
     * 获取hashKey对应的所有键值
     * @param key 键
     * @return 对应的多个键值
     */
    public Map<Object, Object> hmget(String key){
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * HashSet
     * @param key 键
     * @param map 对应多个键值
     * @return true 成功 false 失败
     */
    public boolean hmset(String key, Map<String, Object> map){
        try {
            redisTemplate.opsForHash().putAll(key, map);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * HashSet 并设置时间
     * @param key 键
     * @param map 对应多个键值
     * @param time 时间(秒)
     * @return true成功 false失败
     */
    public boolean hmset(String key, Map<String, Object> map, long time){
        try {
            redisTemplate.opsForHash().putAll(key, map);
            if(time>0){
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     * @param key 键
     * @param item 项
     * @param value 值
     * @return true 成功 false失败
     */
    public boolean hset(String key, String item, Object value) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     * @param key 键
     * @param item 项
     * @param value 值
     * @param time 时间(秒)  注意:如果已存在的hash表有时间,这里将会替换原有的时间
     * @return true 成功 false失败
     */
    public boolean hset(String key, String item, Object value, long time) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            if(time>0){
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除hash表中的值
     * @param key 键 不能为null
     * @param item 项 可以使多个 不能为null
     */
    public void hdel(String key, Object... item){
        redisTemplate.opsForHash().delete(key,item);
    }

    /**
     * 判断hash表中是否有该项的值
     * @param key 键 不能为null
     * @param item 项 不能为null
     * @return true 存在 false不存在
     */
    public boolean hHasKey(String key, String item){
        return redisTemplate.opsForHash().hasKey(key, item);
    }

    /**
     * hash递增 如果不存在,就会创建一个 并把新增后的值返回
     * @param key 键
     * @param item 项
     * @param by 要增加几(大于0)
     * @return
     */
    public double hincr(String key, String item, double by){
        return redisTemplate.opsForHash().increment(key, item, by);
    }

    /**
     * hash递减
     * @param key 键
     * @param item 项
     * @param by 要减少记(小于0)
     * @return
     */
    public double hdecr(String key, String item, double by){
        return redisTemplate.opsForHash().increment(key, item,-by);
    }

    //============================set=============================
    /**
     * 根据key获取Set中的所有值
     * @param key 键
     * @return
     */
    public Set<Object> sGet(String key){
        try {
            return redisTemplate.opsForSet().members(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据value从一个set中查询,是否存在
     * @param key 键
     * @param value 值
     * @return true 存在 false不存在
     */
    public boolean sHasKey(String key, Object value){
        try {
            return redisTemplate.opsForSet().isMember(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将数据放入set缓存
     * @param key 键
     * @param values 值 可以是多个
     * @return 成功个数
     */
    public long sSet(String key, Object...values) {
        try {
            return redisTemplate.opsForSet().add(key, values);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 将set数据放入缓存
     * @param key 键
     * @param time 时间(秒)
     * @param values 值 可以是多个
     * @return 成功个数
     */
    public long sSetAndTime(String key, long time, Object...values) {
        try {
            Long count = redisTemplate.opsForSet().add(key, values);
            if(time>0) {
                expire(key, time);
            }
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 获取set缓存的长度
     * @param key 键
     * @return
     */
    public long sGetSetSize(String key){
        try {
            return redisTemplate.opsForSet().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 移除值为value的
     * @param key 键
     * @param values 值 可以是多个
     * @return 移除的个数
     */
    public long setRemove(String key, Object...values) {
        try {
            Long count = redisTemplate.opsForSet().remove(key, values);
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    //===============================list=================================

    /**
     * 获取list缓存的内容
     * @param key 键
     * @param start 开始
     * @param end 结束  0 到 -1代表所有值
     * @return
     */
    public List<Object> lGet(String key, long start, long end){
        try {
            return redisTemplate.opsForList().range(key, start, end);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取list缓存的长度
     * @param key 键
     * @return
     */
    public long lGetListSize(String key){
        try {
            return redisTemplate.opsForList().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 通过索引 获取list中的值
     * @param key 键
     * @param index 索引  index>=0时， 0 表头，1 第二个元素，依次类推；index<0时，-1，表尾，-2倒数第二个元素，依次类推
     * @return
     */
    public Object lGetIndex(String key, long index){
        try {
            return redisTemplate.opsForList().index(key, index);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将list放入缓存
     * @param key 键
     * @param value 值
     * @return
     */
    public boolean lSet(String key, Object value) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将list放入缓存
     * @param key 键
     * @param value 值
     * @param time 时间(秒)
     * @return
     */
    public boolean lSet(String key, Object value, long time) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将list放入缓存
     * @param key 键
     * @param value 值
     * @return
     */
    public boolean lSet(String key, List<Object> value) {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将list放入缓存
     * @param key 键
     * @param value 值
     * @param time 时间(秒)
     * @return
     */
    public boolean lSet(String key, List<Object> value, long time) {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据索引修改list中的某条数据
     * @param key 键
     * @param index 索引
     * @param value 值
     * @return
     */
    public boolean lUpdateIndex(String key, long index, Object value) {
        try {
            redisTemplate.opsForList().set(key, index, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 移除N个值为value
     * @param key 键
     * @param count 移除多少个
     * @param value 值
     * @return 移除的个数
     */
    public long lRemove(String key, long count, Object value) {
        try {
            Long remove = redisTemplate.opsForList().remove(key, count, value);
            return remove;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 模糊查询   before为在key前加* after为在key后加* 其余情况均为前后都加*
     * @param key
     * @param place
     * @return
     */
    public Map<String, String> queryInfoAboutKey(String key, String place) {
        String keyPattern = "*" + key + "*";
        if(CommonCode.BEFORE.getCode().equals(place)){
            keyPattern = "*" + key ;
        }else if(CommonCode.AFTER.getCode().equals(place)){
            keyPattern = key + "*";
        }
        Map<String, String> res = new HashMap<>();

        Set<String> keys = redisTemplate.keys(keyPattern);
        try {
            for (String keyTmp : keys) {

                DataType keyType = redisTemplate.type(keyTmp);
                if (keyType.code().equals("none")) {
                } else if (keyType.code().equals("list")) {
                    res.put(keyTmp, JSONObject.toJSONString(redisTemplate.opsForList().range(keyTmp, 0, -1)));
                } else if (keyType.code().equals("set")) {
                    res.put(keyTmp, JSONObject.toJSONString(redisTemplate.opsForSet().members(keyTmp)));
                } else if (keyType.code().equals("string")) {
                    res.put(keyTmp, (String) redisTemplate.opsForValue().get(keyTmp));
                } else if (keyType.code().equals("zset")) {
                    res.put(keyTmp, JSONObject.toJSONString(redisTemplate.opsForZSet().range(keyTmp, 0, -1)));
                } else if (keyType.code().equals("hash")) {
                    res.put(keyTmp, JSONObject.toJSONString(redisTemplate.opsForHash().values(keyTmp)));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * 模糊查询   before为在key前加* after为在key后加* 其余情况均为前后都加*
     * @param key
     * @param place
     * @return
     */
    public JSONObject queryJSONAboutKey(String key, String place) {
        String keyPattern = "*" + key + "*";
        if(CommonCode.BEFORE.getCode().equals(place)){
            keyPattern = "*" + key ;
        }else if(CommonCode.AFTER.getCode().equals(place)){
            keyPattern = key + "*";
        }
        JSONObject res = new JSONObject();

        Set<String> keys = redisTemplate.keys(keyPattern);
        try {
            for (String keyTmp : keys) {

                DataType keyType = redisTemplate.type(keyTmp);
                if (keyType.code().equals("none")) {
                } else if (keyType.code().equals("list")) {
                    res.put(keyTmp, JSONObject.toJSONString(redisTemplate.opsForList().range(keyTmp, 0, -1)));
                } else if (keyType.code().equals("set")) {
                    res.put(keyTmp, JSONObject.toJSONString(redisTemplate.opsForSet().members(keyTmp)));
                } else if (keyType.code().equals("string")) {
                    res.put(keyTmp, (String) redisTemplate.opsForValue().get(keyTmp));
                } else if (keyType.code().equals("zset")) {
                    res.put(keyTmp, JSONObject.toJSONString(redisTemplate.opsForZSet().range(keyTmp, 0, -1)));
                } else if (keyType.code().equals("hash")) {
                    res.put(keyTmp, JSONObject.toJSONString(redisTemplate.opsForHash().values(keyTmp)));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * 模糊查询   before为在key前加* after为在key后加* 其余情况均为前后都加*
     * @param key
     * @param place
     * @return
     */
    public List<String> queryListInfoAboutKey(String key, String place) {
        String keyPattern = "*" + key + "*";
        if(CommonCode.BEFORE.getCode().equals(place)){
            keyPattern = "*" + key ;
        }else if(CommonCode.AFTER.getCode().equals(place)){
            keyPattern = key + "*";
        }
        List<String> res = new ArrayList<>();
        Set<String> keys = redisTemplate.keys(keyPattern);
        try {
            for (String keyTmp : keys) {

                DataType keyType = redisTemplate.type(keyTmp);
                if (keyType.code().equals("none")) {
                } else if (keyType.code().equals("list")) {
                    res.add(JSONObject.toJSONString(redisTemplate.opsForList().range(keyTmp, 0, -1)));
                } else if (keyType.code().equals("set")) {
                    res.add(JSONObject.toJSONString(redisTemplate.opsForSet().members(keyTmp)));
                } else if (keyType.code().equals("string")) {
                    res.add(StringUtils.getString(redisTemplate.opsForValue().get(keyTmp)));
                } else if (keyType.code().equals("zset")) {
                    res.add(JSONObject.toJSONString(redisTemplate.opsForZSet().range(keyTmp, 0, -1)));
                } else if (keyType.code().equals("hash")) {
                    res.add(JSONObject.toJSONString(redisTemplate.opsForHash().values(keyTmp)));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }


    /**
     * 模糊查询所有的key   before为在key前加* after为在key后加* 其余情况均为前后都加*
     * @param key
     * @param place
     * @return
     */
    public Set<String> queryKeySetAboutKey(String key, String place) {
        String keyPattern = "*" + key + "*";
        if(CommonCode.BEFORE.getCode().equals(place)){
            keyPattern = "*" + key ;
        }else if(CommonCode.AFTER.getCode().equals(place)){
            keyPattern = key + "*";
        }
        Set<String> keys = redisTemplate.keys(keyPattern);
        return keys;
    }



}
