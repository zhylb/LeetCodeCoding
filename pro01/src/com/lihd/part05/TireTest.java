package com.lihd.part05;

import org.junit.Test;

import java.util.Arrays;

/**
 * RadixSort
 * CountSort
 *
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/4 16:05
 */
public class TireTest {
    PrefixTree prefixTree = new PrefixTree();
    Tire tire = new Tire();

    public int randomValue(int b, int e){
        return (int) ((e - b) * Math.random() + b);
    }
    public String  generateString(int strLength){
        int len = randomValue(0,strLength + 1);
        char[] chars = new char[len];
        for (int i = 0; i < chars.length; i++) {
            chars[i] = (char) randomValue('a','z' + 1);
        }
        return new String(chars);
    }

    public void insert(String str){
        prefixTree.insert(str);
        tire.insert(str);
    }

    public void  compareResult(int a, int b,String str){
        if(a != b){
            System.out.println("TireTest.search 测试失败");
            System.out.println("str = " + str);
            System.out.println("a = " + a);
            System.out.println("b = " + b);
            System.exit(-1);
        }
    }

    public void search(String str){
        int a = prefixTree.search(str);
        int b = tire.search(str);
        compareResult(a,b,str);
    }

    public void prefixNumber(String str){
        int a = prefixTree.prefixNumber(str);
        int b = tire.prefixNum(str);
        compareResult(a,b,str);
    }

    public void delete(String s){
        prefixTree.delete(s);
        tire.delete(s);
    }

    @Test
    public void testBatch(){
        for (int i = 0; i < 1000; i++) {
            //测试一千次
            String str = generateString(10);
            insert(str);
            search(str);
            prefixNumber(str);
            if(randomValue(0,4) == 0){
                //四份之一概率发动有效删除
                delete(str);
            }
        }
        System.out.println("成功");
    }

    @Test
    public void test01(){
        Tire tire = new Tire();
        tire.insert("aa");
        tire.insert("cc");
        tire.insert("aa");
        tire.insert("aaa");
        tire.insert("a");
        System.out.println(tire.search("aa"));
        System.out.println(tire.prefixNum("a"));
        tire.delete("aa");
        System.out.println(tire.search("aa"));
        System.out.println(tire.prefixNum("a"));
    }
}
