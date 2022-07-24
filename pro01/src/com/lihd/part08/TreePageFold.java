package com.lihd.part08;

import org.junit.Test;

/**
 *
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/6 19:37
 */
public class TreePageFold {
    @Test
    public void test01(){
        printPageFold(10);
    }
    public static void printPageFold(int N){
        printPageFold(1,N,true);
    }
    public static void printPageFold(int cur,int N,boolean isConcave){
        if(cur > N) return;
        printPageFold(cur + 1,N,true);
        String str = isConcave ? "凹":"凸";
        System.out.print(str);
        printPageFold(cur + 1,N,false);
    }
}
