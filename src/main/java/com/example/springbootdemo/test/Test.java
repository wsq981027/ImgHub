package com.example.springbootdemo.test;

import com.example.springbootdemo.SpringbootdemoApplication;
import com.example.springbootdemo.model.hui.USERS;
import com.example.springbootdemo.utils.StringUtil;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Test {
    @Autowired
    RedisTemplate redisTemplate;

//    @org.junit.Test
//    public static void main(String[] args) {
////        String str="game ko fight";
////        ArrayList list= StringUtil.split(str);
////        for (int i=0;i<list.size();i++){
////            System.out.println(list.get(i));
////        }
//
//    }

    @org.junit.Test
    public void test(){
//        RedisTemplate redisTemplate=new RedisTemplate();
//        USERS users=new USERS();
//        users.setId(55);
//        users.setName("wzh");
//        users.setPassword("123456");
//        ValueOperations<String,USERS> valueOperations=redisTemplate.opsForValue();
//        valueOperations.set("user1",users);
//        boolean exist=redisTemplate.hasKey("user1");
//        System.out.println("redis是否存在key:"+exist);
//        USERS getUser= (USERS) redisTemplate.opsForValue().get("user1");
//        System.out.println("从redis中获取的对象:"+getUser);


    }
}
