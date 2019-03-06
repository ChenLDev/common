package com.boco.dao.entity;

import lombok.Data;

/**
 * 配置实体类
 *
 * @author ChenLiang
 * @create 2019 02 11
 */
@Data
public class Config {
    private String cuid;
    private String name;
    private String val;
    private String des;
    private String type;
    private String state;
    private String createDate;
    private String modifyDate;
}
