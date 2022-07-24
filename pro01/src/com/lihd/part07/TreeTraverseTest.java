package com.lihd.part07;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/5 16:55
 */
public class TreeTraverseTest {
    private static final int TREE_VALUE = 10;

    TreeTraverse treeTraverse = new TreeTraverse();
    TreeTraverseIter treeTraverseIter = new TreeTraverseIter();

    Random random = new Random();

    public Node generateTree(int maxNodeNum){

        Queue<Node> queue = new LinkedList<>();
        Node root = new Node(0);
        Node left;
        Node right;
        queue.add(root);
        int i = 0;
        while(!queue.isEmpty() && i < maxNodeNum){
            Node node = queue.poll();
            if(random.nextBoolean()){
                left = new Node(random.nextInt(TREE_VALUE));
                node.left = left;
                queue.add(left);
                i ++;
            }else{
                node.left = null;
            }
            if(random.nextBoolean()){
                right = new Node(random.nextInt(TREE_VALUE));
                node.right = right;
                queue.add(right);
                i ++;
            }else {
                node.right = null;
            }
        }
        return root;
    }

    @Test
    public void test01(){
        Node tree = generateTree(8);
        treeTraverse.post(tree);
        System.out.println("----");
        treeTraverseIter.post(tree);
    }

}
