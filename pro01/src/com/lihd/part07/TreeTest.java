package com.lihd.part07;

import org.junit.Test;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/6 0:21
 */
public class TreeTest {



    @Test
    public void test02(){

    }

    @Test
    public void test01(){
        for (int i = 0; i < 1000; i++) {
            Node tree = TreeUtils.generateTree(10);
            int maxWidth = TreeUtils.getMaxWidth(tree);
            int maxWidth2 = TreeUtils.getMaxWidth2(tree);
            if(maxWidth != maxWidth2){
                System.out.println("失败");
                System.out.println("maxWidth = " + maxWidth);
                System.out.println("maxWidth2 = " + maxWidth2);
                System.exit(0);
            }
        }
        System.out.println("成功");
    }
}
