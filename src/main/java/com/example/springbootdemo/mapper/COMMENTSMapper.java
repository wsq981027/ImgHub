package com.example.springbootdemo.mapper;

import com.example.springbootdemo.model.COMMENTS;

import java.util.List;

public interface COMMENTSMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(COMMENTS record);

    int insertSelective(COMMENTS record);

    COMMENTS selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(COMMENTS record);

    int updateByPrimaryKey(COMMENTS record);

    List selectByImgid(int imgid);

    int deleteByImgid(int imgid);

    List selectAll();
}