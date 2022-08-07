package com.lihd.part07;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/5 16:37
 */
public class TreeTraverse {


    public void pre(Node node){
        if(node == null) return;
        System.out.println(node.value);
        pre(node.left);
        pre(node.right);
    }

    public void in(Node node){
        if(node == null) return;
        in(node.left);
        System.out.println(node.value);
        in(node.right);
    }

    public void post(Node node){
        if(node == null) return;
        post(node.left);
        post(node.right);
        System.out.println(node.value);
    }





}
