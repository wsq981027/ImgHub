package com.example.springbootdemo.mapper;

import com.example.springbootdemo.model.COMPLAINS;

import java.util.List;

public interface COMPLAINSMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(COMPLAINS record);

    int insertSelective(COMPLAINS record);

    COMPLAINS selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(COMPLAINS record);

    int updateByPrimaryKey(COMPLAINS record);

    List selectAll();
}