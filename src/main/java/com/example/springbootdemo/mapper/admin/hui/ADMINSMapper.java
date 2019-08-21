package com.example.springbootdemo.mapper.admin.hui;

import com.example.springbootdemo.model.admin.hui.ADMINS;

import java.util.List;

public interface ADMINSMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ADMINS record);

    int insertSelective(ADMINS record);

    ADMINS selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ADMINS record);

    int updateByPrimaryKey(ADMINS record);

    ADMINS selectByName(String name);


}