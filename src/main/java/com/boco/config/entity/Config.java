package com.boco.config.entity;


import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 配置条目，对应具体配置项
 *
 * @author ChenLiang
 * @create 2019 01 24
 */
@Data
@AllArgsConstructor
public class Config {
    private String name;
    private String val;
}
