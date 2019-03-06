package com.boco.dao.mapper;

import com.boco.dao.entity.ConfigEntity;
import com.boco.dao.entity.ConfigEntityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ConfigEntityMapper {
    int countByExample(ConfigEntityExample example);

    int deleteByExample(ConfigEntityExample example);

    int deleteByPrimaryKey(String cuid);

    int insert(ConfigEntity record);

    int insertSelective(ConfigEntity record);

    List<ConfigEntity> selectByExample(ConfigEntityExample example);

    ConfigEntity selectByPrimaryKey(String cuid);

    int updateByExampleSelective(@Param("record") ConfigEntity record, @Param("example") ConfigEntityExample example);

    int updateByExample(@Param("record") ConfigEntity record, @Param("example") ConfigEntityExample example);

    int updateByPrimaryKeySelective(ConfigEntity record);

    int updateByPrimaryKey(ConfigEntity record);
}