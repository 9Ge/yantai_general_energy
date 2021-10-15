package com.enercomn.web.service.impl;

import com.enercomn.util.RedisUtil;
import com.enercomn.util.StringUtils;
import com.enercomn.web.bean.TbEnergyDataCollectorRelation;
import com.enercomn.web.bean.TbEnergyDataCollectorValue;
import com.enercomn.web.mapper.TbEnergyDataCollectorRelationMapper;
import com.enercomn.web.mapper.TbEnergyDataCollectorValueMapper;
import com.enercomn.web.service.TbEnergyDataCollectorRelationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

/**
 * @Date: 2021/10/12 17:49<br/>
 * @Author: yangcheng
 * @Version: 1.0
 */
@Slf4j
@Service
public class TbEnergyDataCollectorRelationServiceImpl implements TbEnergyDataCollectorRelationService {

    private final TbEnergyDataCollectorRelationMapper tbEnergyDataCollectorRelationMapper;
    private final TbEnergyDataCollectorValueMapper tbEnergyDataCollectorValueMapper;
    private final RedisUtil redisUtil;

    public TbEnergyDataCollectorRelationServiceImpl(TbEnergyDataCollectorRelationMapper tbEnergyDataCollectorRelationMapper, TbEnergyDataCollectorValueMapper tbEnergyDataCollectorValueMapper, RedisUtil redisUtil) {
        this.tbEnergyDataCollectorRelationMapper = tbEnergyDataCollectorRelationMapper;
        this.tbEnergyDataCollectorValueMapper = tbEnergyDataCollectorValueMapper;
        this.redisUtil = redisUtil;
    }

    @Override
    public List<TbEnergyDataCollectorValue> redisMediumRelation() {
        List<TbEnergyDataCollectorRelation> collectors = tbEnergyDataCollectorRelationMapper.selectAll();
        List<TbEnergyDataCollectorValue> pushEnergyDataCollectorLogList = new ArrayList<>();
        collectors.forEach(collector -> {
            String redisKey = collector.getRedisKey();
            String value = redisUtil.getString(redisKey);
            if (StringUtils.isNotEmpty(value)) {
                TbEnergyDataCollectorValue tbPushEnergyDataCollectorLog = new TbEnergyDataCollectorValue();
                tbPushEnergyDataCollectorLog.setId(StringUtils.getUUID());
                tbPushEnergyDataCollectorLog.setRedisKey(redisKey);
                tbPushEnergyDataCollectorLog.setRedisValue(value);
                tbPushEnergyDataCollectorLog.setDataCode(collector.getDataCode());
                tbPushEnergyDataCollectorLog.setCollectorTime(new Date());
                pushEnergyDataCollectorLogList.add(tbPushEnergyDataCollectorLog);
            }
        });
        try {
            tbEnergyDataCollectorValueMapper.saveBatch(pushEnergyDataCollectorLogList);
        } catch (Exception e) {
            log.error("保存采集数据失败,e:{}",e);
        }
        return pushEnergyDataCollectorLogList;

    }

    /**
     * @param param
     * @return
     */
    @Override
    public boolean addData(TbEnergyDataCollectorRelation param) {
        try {
            param.setId(StringUtils.getUUID());
            return tbEnergyDataCollectorRelationMapper.insert(param) >= 0;
        } catch (Exception e) {
            log.info("{}", e);
            throw new RuntimeException("新增异常：" + e.getMessage());
        }
    }


    /**
     * @param param
     * @return
     */
    @Override
    public boolean deleteData(TbEnergyDataCollectorRelation param) {
        try {
            TbEnergyDataCollectorRelation delParam = new TbEnergyDataCollectorRelation();
            delParam.setId(param.getId());
            return tbEnergyDataCollectorRelationMapper.delete(delParam) == 1;
        } catch (Exception e) {
            log.info("{}", e);
            throw new RuntimeException("删除异常：" + e.getMessage());
        }
    }

    ;

    /**
     * @param param
     * @return
     */
    @Override
    public boolean updateData(TbEnergyDataCollectorRelation param) {
        try {
            return tbEnergyDataCollectorRelationMapper.updateByPrimaryKey(param) >= 0;
        } catch (Exception e) {
            log.info("{}", e);
            throw new RuntimeException("修改异常：" + e.getMessage());
        }
    }

    ;

    /**
     * @return
     */
    @Override
    public List<TbEnergyDataCollectorRelation> findDataList(TbEnergyDataCollectorRelation relation) {
        return tbEnergyDataCollectorRelationMapper.selectAll();
    }

    @Override
    public List<TbEnergyDataCollectorRelation> queryByMap(Map<String,String> map) {
        return tbEnergyDataCollectorRelationMapper.selectByExample(getExampleByMap(map));
    }

    private Example getExampleByMap(Map<String,String> map){
        Set<Map.Entry<String, String>> entries = map.entrySet();
        Example example = new Example(TbEnergyDataCollectorRelation.class);
        Example.Criteria criteria = example.createCriteria();
        for (Map.Entry<String, String> m:entries){
            criteria.andEqualTo(m.getKey(),m.getValue());
        }
        return example;
    }
    @Override
    public List<TbEnergyDataCollectorRelation> deleteByMap(Map<String,String> map) {
        return tbEnergyDataCollectorRelationMapper.selectByExample(getExampleByMap(map));
    }

    @Override
    public boolean saveBatch(List<TbEnergyDataCollectorRelation> tbEnergyDataCollectorRelations){
        try {
            if(tbEnergyDataCollectorRelations.size()<=0){
                return true;
            }
            tbEnergyDataCollectorRelationMapper.saveBatch(tbEnergyDataCollectorRelations);
        } catch (Exception e) {
           log.error("设置redis和关联关系失败");
        }
        return true;
    }

}
