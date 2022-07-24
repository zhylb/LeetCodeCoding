package com.lihd.part07;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/5 23:15
 */
public class TreeUtils {
    private static final int TREE_VALUE = 10;
    private static final Random random = new Random();


    public static Node generateTree(int maxNodeNum){
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


    public static boolean equals(Node treeA, Node treeB){
        if(treeA == null && treeB == null){
            //都是null
            return true;
        }else if(treeA != null && treeB != null && treeA.value == treeB.value){
            return equals(treeA.left,treeB.left) && equals(treeA.right, treeB.right);
        }
        return false;
    }

    //判断是否为正则树
    public static boolean isExpTree(Node root){
        if(root == null) return true;//如果能保证用户不传入空 就可以省略这个重复的判空操作
        if(root.left == null && root.right == null){
            //两个孩子都是 null 说明 正确 返回真
            return true;
        }else if(root.left != null && root.right != null){
            //两个孩子都存在 且不为空 ,递归调用
            //返回 : 左孩子是正则 && 右孩子是正则
            return isExpTree(root.left) && isExpTree(root.right);
        }
        //上面两个都不成立 说明只有一个孩子 返回假
        return false;
    }


    //获取树的最大宽度
    public static int getMaxWidth(Node root){
        if(root == null){
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        int maxWidth = 0;
        int curTreeNum = 0;
        queue.add(root);
        Node curEnd = root;
        Node nextEnd = null;
        while(!queue.isEmpty()){
            Node node = queue.poll();
            curTreeNum ++;//对数器还是有用的  嘿嘿 这里需要先加才行 本来放到后面了 因此就错了

            if(node.left != null){
                queue.add(node.left);
                nextEnd = node.left;
            }

            if(node.right != null){
                queue.add(node.right);
                nextEnd = node.right;
            }

            if(node == curEnd){
                //达到终点
                curEnd = nextEnd;
                nextEnd = null;
                maxWidth = Math.max(maxWidth,curTreeNum);
                curTreeNum = 0;
            }
        }
        return maxWidth;
    }

    public static int getMaxWidth2(Node root){
        if(root == null){
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        HashMap<Node, Integer> map = new HashMap<>();
        int curTreeNum = 0;
        int curTreeLevel = 1;
        int maxWidth = 0;
        queue.add(root);
        map.put(root,1);
        while (!queue.isEmpty()){
            Node node = queue.poll();
            Integer tempLevel = map.get(node);
            if(node.left != null){
                queue.add(node.left);
                map.put(node.left, tempLevel + 1);
            }
            if(node.right != null){
                queue.add(node.right);
                map.put(node.right, tempLevel + 1);
            }

            if(tempLevel != curTreeLevel){
                //说明已经到了下一层
                curTreeLevel ++;
                maxWidth = Math.max(maxWidth,curTreeNum);
                curTreeNum = 1;
            }else{
                curTreeNum ++;
            }
        }
        maxWidth = Math.max(maxWidth,curTreeNum);


        return maxWidth;
    }


}
