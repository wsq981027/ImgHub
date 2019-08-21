package com.example.springbootdemo.mapper.hui;

import com.example.springbootdemo.model.hui.USERS;

import java.util.List;

public interface USERSMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(USERS record);

    int insertSelective(USERS record);

    USERS selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(USERS record);

    int updateByPrimaryKey(USERS record);

    List selectAll();

    USERS selectByName(String name);

    int reg(USERS users);

    List selectBanUsers();

    List selectLikeName(String name);

    List selectBanLikeName(String name);
}