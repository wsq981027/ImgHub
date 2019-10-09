package com.example.springbootdemo.mapper.hui;

import com.example.springbootdemo.model.hui.THUMB;

public interface THUMBMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(THUMB record);

    int insertSelective(THUMB record);

    THUMB selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(THUMB record);

    int updateByPrimaryKey(THUMB record);

    THUMB selectByImgAndUser(int imgid,int userid);

    int deleteByImgAndUser(int imgid,int userid);

    int deleteByImg(int imgid);
}