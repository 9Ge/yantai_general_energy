package com.enercomn.web.service.impl;

import com.enercomn.web.bean.TbEnergyDataCollectorRelation;
import com.enercomn.web.bean.TbEnergyDataCollectorValue;
import com.enercomn.web.mapper.TbEnergyDataCollectorRelationMapper;
import com.enercomn.web.mapper.TbEnergyDataCollectorValueMapper;
import com.enercomn.web.service.TbEnergyDataCollectorRelationService;
import com.enercomn.util.RedisUtil;
import com.enercomn.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    public   List<TbEnergyDataCollectorValue> redisMediumRelation(){
        List<TbEnergyDataCollectorRelation> collectors = tbEnergyDataCollectorRelationMapper.selectAll();
        List<TbEnergyDataCollectorValue> pushEnergyDataCollectorLogList = new ArrayList<>();
        collectors.forEach(collector->{
            String redisKey = collector.getRedisKey();
            String value = redisUtil.getString(redisKey);
            if(StringUtils.isNotEmpty(value)){
                TbEnergyDataCollectorValue tbPushEnergyDataCollectorLog = new TbEnergyDataCollectorValue();
                tbPushEnergyDataCollectorLog.setId(StringUtils.getUUID());
                tbPushEnergyDataCollectorLog.setRedisKey(redisKey);
                tbPushEnergyDataCollectorLog.setRedisValue(value);
                tbPushEnergyDataCollectorLog.setDataCode(collector.getDataCode());
                tbPushEnergyDataCollectorLog.setPushTime(new Date());
                tbPushEnergyDataCollectorLog.setCollectorTime(new Date());
                pushEnergyDataCollectorLogList.add(tbPushEnergyDataCollectorLog);
            }
        });
        try {
            tbEnergyDataCollectorValueMapper.saveBatch(pushEnergyDataCollectorLogList);
        } catch (Exception e) {
            log.error("保存采集数据失败");
        }
        return pushEnergyDataCollectorLogList;

    }



}
