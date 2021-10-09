package com.enercomn.mapper;

import com.enercomn.bean.TbEnergyData;
import lombok.Data;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Date: 2021/10/9 11:59<br/>
 * @Author: yangcheng
 * @Version: 1.0
 */
@Repository
public interface TbEnergyDataMapper extends Mapper<TbEnergyData> {

}
