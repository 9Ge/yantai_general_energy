package com.enercomn.util.constant;

public enum RedisCode {



    SYSTEM_DETAIL_BEAN("systemDetailBean.","系统图明细前缀"),
    BIM_DETAIL("BIM_detail.","BIM图明细前缀"),

    BIM_MONITOR("BIM_monitor.","BIM图监控前缀"),

    /**
     * 后面加上项目id，元件id，属性id
     */
    ATTR_VALUE("attrValue.","实时属性值前缀"),

    ATTR_VALUE_MINIMUN("attrValueMinimun.","当日属性最小值前缀"),

    MAX_DATA("maxData.","当日最大值前缀"),

    HUIZHONG_MEDIUM_ENERGY("huiZhongMediumEnergy.","汇众首页介质能耗查询前缀"),

    HUIZHONG_OPEN_DEVICE("huiZhongOpenDevice.","汇众首页空压机开启率前缀"),

    HUIZHONG_ENERGY_DEVICE("huiZhongEnergyDevice.","汇众首页环境监控平均值前缀"),

    SYSTEM_CONTROL_INFO("systemControlInfo.","系统控制前缀"),

    TAGS_REP_INFO("tagsRepInfo.","配置信息前缀"),

    CONFIG_INFO("configInfo.","配置信息前缀"),

    ALARM_RULE("alarmRule.","报警规则"),

    RUNNING_STATE("runningState","报警状态"),

    COLLECT_RATE("collectRate.","采集频率"),

    COLLECT_GATEWAYINFO("collectGatewayInfo.","采集器网关信息"),

    SNAPSHOT("snapshot","快照"),

    /**
     * 后面加项目id.角色id
     */
    AREA_JSON("areaJson.","区域树形图信息前缀"),

    H5_PAGE_SHOW("h5PageShow","h5页面展示"),

    COPCURVE_CONFIG("copCurveConfig","cop曲线配置"),

    ECONOMIZE_ENERGY("economizeEnergy","节能配置"),


    HUIZHONG("huiZhong","汇众首页查询数据前缀"),

    ROLE_INFO("roleInfo","超级管理员和管理员的角色信息"),

    TABLE_INFO("tableInfo.","项目表信息"),

    H5_SYSTEM_DETAIL_CONFIG("h5SystemDetailConfig.","h5系统图明细配置前缀"),

    H5_SYSTEM_DETAIL_BEAN("h5SystemDetailBean.","h5系统图明细前缀"),

    COLLECTDATA("collectData.","采集数据"),

    COLLECTSTATE("collectState.","采集器状态"),

    SYSTEM_POINT_BEAN("systemPointBean.","系统图点位前缀"),

    BACNET_CONTROL("bacnetControl.","bacnet协议控制前缀"),

    TAG_WITH_NODE_RED_CODE("tagWithNodeRedCode.","tag和nodeRedCode对应关系"),

    CUMULATIVE_DATA_CODE("cumulativeDataCode.","累计cop计算配置"),

    CUMULATIVE_TOTAL_DATA("cumulativeTotalData.","累计总值"),

    OLD_INFO_TOTAL("oldInfoTotal.","历史info总值"),

    OLD_ENERGY_ANALYSIS_TOTAL("oldEnergyAnalysisTotal.","能耗分析累计总值"),
    ;

    private String code;
    private String name;

    RedisCode(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.name = code;
    }

}
