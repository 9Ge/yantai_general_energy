package com.enercomn.web.service.impl;

import com.enercomn.util.ListPageUtil;
import com.enercomn.util.constant.PageBean;
import com.enercomn.util.constant.PageObject;
import com.enercomn.web.bean.TbEnergyRequestLog;
import com.enercomn.web.mapper.TbEnergyRequestLogMapper;
import com.enercomn.web.service.TbEnergyRequestLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @Date: 2021/10/12 15:44<br/>
 * @Author: yangcheng
 * @Version: 1.0
 */
@Service
public class TbEnergyRequestLogServiceImpl implements TbEnergyRequestLogService {

    @Autowired
    private TbEnergyRequestLogMapper tbEnergyRequestLogMapper;

    @Override
    public PageBean queryLog(PageObject<TbEnergyRequestLog> pageObject) {
        Example example = new Example(TbEnergyRequestLog.class);
        example.orderBy("requestTime").desc();
        List<TbEnergyRequestLog> tbEnergyRequestLogs = tbEnergyRequestLogMapper.selectByExample(example);
        return ListPageUtil.pageInfo(pageObject, tbEnergyRequestLogs);
    }

}
