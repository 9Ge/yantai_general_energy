package com.enercomn.web.mapper;


import com.enercomn.web.bean.TbEnergyDataCollectorRelation;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface TbEnergyDataCollectorRelationMapper extends Mapper<TbEnergyDataCollectorRelation> {
    int saveBatch(List<TbEnergyDataCollectorRelation> list);
}