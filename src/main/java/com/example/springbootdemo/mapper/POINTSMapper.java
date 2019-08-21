package com.example.springbootdemo.mapper;

import com.example.springbootdemo.model.POINTS;

public interface POINTSMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(POINTS record);

    int insertSelective(POINTS record);

    POINTS selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(POINTS record);

    int updateByPrimaryKey(POINTS record);

    POINTS selectByUid(int uid);
}