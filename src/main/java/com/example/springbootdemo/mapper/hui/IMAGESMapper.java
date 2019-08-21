package com.example.springbootdemo.mapper.hui;

import com.example.springbootdemo.model.hui.IMAGES;

import java.util.List;

public interface IMAGESMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(IMAGES record);

    int insertSelective(IMAGES record);

    IMAGES selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(IMAGES record);

    int updateByPrimaryKey(IMAGES record);

    List selectAll();

    List selectCollect(int userid);

    List selectLikeNameOrIntro(String name);

    List selectByType(int typeid);

    List selectAllOrderByThumb();

    List selectByUser(int userid);

    List selectPurchase(int uid);
}