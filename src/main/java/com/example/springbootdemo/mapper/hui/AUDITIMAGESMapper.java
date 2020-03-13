package com.example.springbootdemo.mapper.hui;

import com.example.springbootdemo.model.hui.AUDITIMAGES;

import java.util.List;

public interface AUDITIMAGESMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AUDITIMAGES record);

    int insertSelective(AUDITIMAGES record);

    AUDITIMAGES selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AUDITIMAGES record);

    int updateByPrimaryKey(AUDITIMAGES record);

    List selectAll();

    List selectLikeName(String name);

    AUDITIMAGES selectByImgname(String imgname);
}