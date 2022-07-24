package com.lihd.part08;


import org.junit.Test;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/6 15:43
 */
public class TreeTest {

    @Test
    public void test01(){
        Node tree = TreeUtils.generateTree(10);
        TreeUtils.printTree(tree);
    }
}
