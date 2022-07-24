package com.lihd.part07;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 序列化和反序列化
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/5 20:10
 */
public class TreeTrans {

    public Queue<String> perSerialize(Node root){
        Queue<String> queue = new LinkedList<>();
        pres(root, queue);
        return queue;
    }

    public void pres(Node tree, Queue<String> queue){
        if(tree == null){
            queue.add(null);
        }else{
            queue.add(""+tree.value);
            pres(tree.left,queue);
            pres(tree.right,queue);
        }
    }

    public Node preB(Queue<String> queue){
        String poll = queue.poll();
        if(poll == null){
            return null;
        }else{
            Node node = new Node(Integer.parseInt(poll));
            node.left = preB(queue);
            node.right = preB(queue);
            return node;
        }
    }

    public Node perDeserialize(Queue<String> queue){
        if(queue == null || queue.isEmpty()){
            return null;
        }
        return preB(queue);
    }
    //使用宽度优先
    public Queue<String> perSerialize2(Node root){
        if(root == null){
            return null;
        }
        Queue<String> resQueue = new LinkedList<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        resQueue.add(String.valueOf(root.value));
        while(!queue.isEmpty()){
            Node poll = queue.poll();

            if(poll.left != null){
                queue.add(poll.left);
                resQueue.add(String.valueOf(poll.left.value));
            }else {
                resQueue.add(null);
            }

            if (poll.right != null) {
                queue.add(poll.right);
                resQueue.add(String.valueOf(poll.right.value));
            }else {
                resQueue.add(null);
            }
        }
        return resQueue;
    }

    public Node perDeserialize2(Queue<String> queue){
        if(queue == null || queue.isEmpty()){
            return null;
        }
        //root一定不是null
        Node root = generateNode(queue.poll());
        if(root == null){
            return null;
        }
        Queue<Node> help = new LinkedList<>();
        help.add(root);
        while(!help.isEmpty()){
            Node poll = help.poll();
            poll.left = generateNode(queue.poll());
            poll.right = generateNode(queue.poll());

            if(poll.left != null){
                help.add(poll.left);
            }

            if(poll.right != null){
                help.add(poll.right);
            }

        }
        return root;
    }

    public Node generateNode(String s){
        if(s == null){
            return null;
        }else{
            return new Node(Integer.parseInt(s));
        }
    }




    @Test
    public void test01(){
        for (int i = 0; i < 1000; i++) {
            Node tree = TreeUtils.generateTree(5);
            Queue<String> queue = perSerialize2(tree);
            Node newTree = perDeserialize2(queue);
            boolean equals = TreeUtils.equals(tree, newTree);
            if(!equals){
                System.out.println("失败");
                System.exit(0);
            }
        }
        System.out.println("成功");
    }
}
