package com.enercomn.web.service;

import com.enercomn.web.bean.TbEnergyDataCollectorRelation;
import com.enercomn.web.bean.TbEnergyDataCollectorValue;

import java.util.List;
import java.util.Map;

/**
 * @Date: 2021/10/12 17:49<br/>
 * @Author: yangcheng
 * @Version: 1.0
 */
public interface TbEnergyDataCollectorRelationService {
    List<TbEnergyDataCollectorValue> redisMediumRelation();


    /**
     * @param param
     * @return
     */
     boolean addData(TbEnergyDataCollectorRelation param);

    /**
     * @param param
     * @return
     */
     boolean deleteData(TbEnergyDataCollectorRelation param);

    /**
     * @param param
     * @return
     */
     boolean updateData(TbEnergyDataCollectorRelation param);

    /**
     * @return
     */
     List<TbEnergyDataCollectorRelation> findDataList(TbEnergyDataCollectorRelation relation);
    boolean saveBatch(List<TbEnergyDataCollectorRelation> tbEnergyDataCollectorRelations);
    List<TbEnergyDataCollectorRelation> queryByMap(Map<String,String> map);
    List<TbEnergyDataCollectorRelation> deleteByMap(Map<String,String> map);
}
