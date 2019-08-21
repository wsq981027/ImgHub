package com.example.springbootdemo.mapper.hui;

import com.example.springbootdemo.model.hui.TYPES;

import java.util.List;

public interface TYPESMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TYPES record);

    int insertSelective(TYPES record);

    TYPES selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TYPES record);

    int updateByPrimaryKey(TYPES record);

    List selectAll();
}