package com.enercomn.util.constant;

import com.enercomn.util.StringUtils;

public enum CommonCode {
    AFTER("after",0, "后面"),

    BEFORE("before",1, "前面"),

    CODE_YES("yes",1, "是"),

    CODE_NO("no",0, "否"),

    CODE_TRUE("true",1, "是"),

    CODE_FALSE("false",0, "否"),

    HUIZHONG_ELECTRICITY("huizhong_electricity",1, "汇众首页用电量"),

    ELECTRIC("electric",1, "用电量"),
    VOLKSWAGEN_TIME("Volkswagen_Time",1, "大众变速器报表时间"),
    VOLKSWAGEN_DATA("Volkswagen_Data",2, "大众变速器报表数据来源"),

    VOLKSWAGEN_DATA_AIR("Volkswagen_Data_Air",3, "大众变速器报表压缩空气数据来源/华域三电共用"),
    VOLKSWAGEN_DATA_STEAM("Volkswagen_Data_Steam",4, "大众变速器报表蒸汽数据来源/华域三电共用"),
    VOLKSWAGEN_DATA_N("Volkswagen_Data_N",5, "华域三电南厂电"),
    VOLKSWAGEN_DATA_WATER("Volkswagen_Data_Water",6, "华域三电水"),
    VOLKSWAGEN_DATA_B("Volkswagen_Data_B",7, "华域三电北厂电"),



    COMPRESSED_AIR("compressed_air",2, "压缩空气"),
    NATURAL_GAS("natural_gas",3, "天然气"),
    WATER("water",4, "水"),
    EFFLUENT("effluent",1, "废水"),
    CURRENCY("currency",1, "通用的七日能耗查询"),
    ENVIRONMENT("environment",2, "环境数据查询"),
    BOILER("boiler",3, "锅炉数据查询"),

    CURRENCY_TYPE("boiler",1, "通用类型"),

    ECONOMIZE_TYPE("economizeType",2, "节能类型"),

    RULE_SEPARATOR("\\|",0, "规则分隔符"),

    CONCLUSION_SEPARATOR("\\;",0, "结论分隔符"),

    JUXTAPOSITION_CONNECT("&&",0, "并列连接符"),

    EXPRESSION_PLACEHOLDER("x",0, "公式占位符"),

    CONTROL_TYPE_NO("controlTypeNo",0, "非控制"),

    CONTROL_TYPE_WORD("controlTypeWord",1, "数字"),

    CONTROL_TYPE_ICON("controlTypeIcon",2, "图标"),

    CONTROL_TYPE_BATCH("controlTypeBatch",3, "批量"),

    CODE_SEPARATOR("/",0, "别名分隔符"),

    DAY("day",1,"日"),
    MONTH("month",2,"月"),
    YEAR("year",3,"年"),
    ;



    private String code;
    private int value;
    private String name;

    CommonCode(String code, int value, String name) {
        this.code = code;
        this.value = value;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getValue() {
        return value;
    }

    public String getStringValue() {
        return StringUtils.getString(value);
    }



    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static String getValueByName(String name) {
        CommonCode[] commonCodes = values();
        for (CommonCode commonCode : commonCodes) {
            if (commonCode.getName().equals(name)) {
                return StringUtils.getString(commonCode.getValue());
            }
        }
        return null;
    }
}
