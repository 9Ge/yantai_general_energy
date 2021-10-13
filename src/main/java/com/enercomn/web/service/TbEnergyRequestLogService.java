package com.enercomn.web.service;

import com.enercomn.web.bean.TbEnergyRequestLog;
import com.enercomn.util.constant.PageBean;
import com.enercomn.util.constant.PageObject;

/**
 * @Date: 2021/10/12 15:44<br/>
 * @Author: yangcheng
 * @Version: 1.0
 */
public interface TbEnergyRequestLogService {
    PageBean queryLog(PageObject<TbEnergyRequestLog> pageObject);
}
