package com.enercomn.web.service;

import com.enercomn.util.constant.PageBean;
import com.enercomn.util.constant.PageObject;
import com.enercomn.web.bean.TbEnergyDataCollectorValue;
import com.enercomn.web.bean.vo.EnergyDataCollectorValueParamVo;

import java.util.List;

/**
 * @Date: 2021/10/13 15:03<br/>
 * @Author: yangcheng
 * @Version: 1.0
 */
public interface TbEnergyDataCollectorValueService {
    PageBean findDataList(PageObject<EnergyDataCollectorValueParamVo> pageObject);
}
