package com.example.springbootdemo.mapper.hui;

import com.example.springbootdemo.model.hui.COLLECTS;

public interface COLLECTSMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(COLLECTS record);

    int insertSelective(COLLECTS record);

    COLLECTS selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(COLLECTS record);

    int updateByPrimaryKey(COLLECTS record);

    COLLECTS selectByImgAndUser(int imgid,int userid);

    int deleteByImgAndUser(int imgid,int userid);

    int deleteByImg(int imgid);
}