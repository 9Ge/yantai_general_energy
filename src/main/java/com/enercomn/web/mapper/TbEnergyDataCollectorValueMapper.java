package com.enercomn.web.mapper;

import com.enercomn.web.bean.TbEnergyDataCollectorValue;
import com.enercomn.web.bean.dto.UploadDeviceData;
import com.enercomn.web.bean.vo.QueryCollectorParamVo;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface TbEnergyDataCollectorValueMapper extends Mapper<TbEnergyDataCollectorValue> {
    int saveBatch(List<TbEnergyDataCollectorValue> list);
    List<UploadDeviceData> queryValue(QueryCollectorParamVo paramVo);
    UploadDeviceData queryValueByCode(QueryCollectorParamVo paramVo);
}