package com.example.springbootdemo.utils;

import java.util.ArrayList;

public class StringUtil {
    public static ArrayList<String> split(String tags){
        ArrayList<String> list=new ArrayList<>();
        StringBuilder temp=new StringBuilder();
        for(int i=0;i<tags.length();i++){
            char tmp=tags.charAt(i);
            if (tmp!=' ') {
                temp.append(tmp);
                if (i==tags.length()-1)
                    list.add(temp.toString());
            }
            else {
                if(i<tags.length()-1&&tags.charAt(i+1)!=' '){
                    list.add(temp.toString());
                    temp.delete(0,temp.length());
                }
            }
        }
        return list;
    }
}
