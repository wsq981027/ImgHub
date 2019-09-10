package com.example.springbootdemo.mapper;

import com.example.springbootdemo.model.DEPOSITS;

import java.util.List;

public interface DEPOSITSMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DEPOSITS record);

    int insertSelective(DEPOSITS record);

    DEPOSITS selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DEPOSITS record);

    int updateByPrimaryKey(DEPOSITS record);

    List selectAll();
}