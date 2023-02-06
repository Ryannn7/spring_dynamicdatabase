package com.tuling.dynamic.datasource.enums;

/**
 * @author liuzongshuai
 * @date 2023/1/29 19:58
 */
public enum DataSourceMark {
    W("W","写数据源"),
    R("R","读数据源");

    private String name;
    private String desc;

    DataSourceMark(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
