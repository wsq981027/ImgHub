package com.example.springbootdemo.mapper;

import com.example.springbootdemo.model.AUDITTAGS;

import java.util.List;

public interface AUDITTAGSMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AUDITTAGS record);

    int insertSelective(AUDITTAGS record);

    AUDITTAGS selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AUDITTAGS record);

    int updateByPrimaryKey(AUDITTAGS record);

    List selectByImgid(int imgid);

    int deleteByImgId(int imgid);
}