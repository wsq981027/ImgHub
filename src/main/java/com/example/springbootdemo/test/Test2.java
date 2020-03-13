package com.example.springbootdemo.test;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

public class Test2 {
    public static void main(String[] args) {
        LastRemaining_Solution(5,3);
    }
    public static int LastRemaining_Solution(int n, int m) {
        if(n<=0)
            return -1;
        LinkedList<Integer> list=new LinkedList();
        for(int i=0;i<n;i++)
            list.add(i);
        int count=n;
        Iterator iterator=list.iterator();
        int res=-1;
        while(true){
            if(count==1)
                break;
            for(int i=0;i<m;i++){
                if(!iterator.hasNext())
                    iterator=list.iterator();
                res= (int) iterator.next();
            }
            iterator.remove();
            count--;
        }
        return list.get(0);
    }
}
