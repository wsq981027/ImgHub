package com.example.springbootdemo.mapper;

import com.example.springbootdemo.model.PURCHASE;

import java.util.List;

public interface PURCHASEMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PURCHASE record);

    int insertSelective(PURCHASE record);

    PURCHASE selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PURCHASE record);

    int updateByPrimaryKey(PURCHASE record);

    PURCHASE selectByUserAndImg(int uid,int imgid);

    int deleteByImg(int imgid);
}