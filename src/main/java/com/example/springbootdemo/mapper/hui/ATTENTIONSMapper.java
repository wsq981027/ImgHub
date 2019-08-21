package com.example.springbootdemo.mapper.hui;

import com.example.springbootdemo.model.hui.ATTENTIONS;

import java.util.List;

public interface ATTENTIONSMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ATTENTIONS record);

    int insertSelective(ATTENTIONS record);

    ATTENTIONS selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ATTENTIONS record);

    int updateByPrimaryKey(ATTENTIONS record);

    List selectFans(int atid);

    List selectAt(int fanid);

    ATTENTIONS selectByAtAndFan(int atid,int fanid);

    int deleteByAtAndUser(int atid,int fanid);
}