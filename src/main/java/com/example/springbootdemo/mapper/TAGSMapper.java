package com.example.springbootdemo.mapper;

import com.example.springbootdemo.model.TAGS;

import java.util.List;

public interface TAGSMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TAGS record);

    int insertSelective(TAGS record);

    TAGS selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TAGS record);

    int updateByPrimaryKey(TAGS record);

    TAGS selectByNameAndImgid(String name,int imgid);

    List selectByImgid(int imgid);

    List selectLikeName(String tag);

    int selectCountByTag(String tag);

    int deleteByImgid(int imgid);
}