package com.enercomn.web.service.impl;

import com.enercomn.util.ListPageUtil;
import com.enercomn.util.StringUtils;
import com.enercomn.util.constant.PageBean;
import com.enercomn.util.constant.PageObject;
import com.enercomn.web.bean.TbEnergyDataCollectorValue;
import com.enercomn.web.bean.vo.EnergyDataCollectorValueParamVo;
import com.enercomn.web.mapper.TbEnergyDataCollectorValueMapper;
import com.enercomn.web.service.TbEnergyDataCollectorValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @Date: 2021/10/13 15:03<br/>
 * @Author: yangcheng
 * @Version: 1.0
 */
@Service
public class TbEnergyDataCollectorValueServiceImpl implements TbEnergyDataCollectorValueService {
    @Autowired
    private TbEnergyDataCollectorValueMapper tbEnergyDataCollectorValueMapper;

    @Override
    public PageBean findDataList(PageObject<EnergyDataCollectorValueParamVo> pageObject) {
        Example example = new Example(TbEnergyDataCollectorValue.class);
        Example.Criteria criteria = example.createCriteria();
        if(StringUtils.isNotEmpty(pageObject.getData().getDataCode())){
            criteria.andLike("dataCode", "%"+pageObject.getData().getDataCode()+"%");
        }
        if(StringUtils.isNotEmpty(pageObject.getData().getRedisKey())){
            criteria.andLike("redisKey", "%"+pageObject.getData().getRedisKey()+"%");
        }
        if(StringUtils.isNotEmpty(pageObject.getData().getStartTime())&&StringUtils.isNotEmpty(pageObject.getData().getEndTime())){
            criteria.andBetween("collectorTime", pageObject.getData().getStartTime(), pageObject.getData().getEndTime());
        }
        example.orderBy("collectorTime").desc();
        List<TbEnergyDataCollectorValue> tbEnergyRequestLogs = tbEnergyDataCollectorValueMapper.selectByExample(example);
        return ListPageUtil.pageInfo(pageObject, tbEnergyRequestLogs);
    }
}
