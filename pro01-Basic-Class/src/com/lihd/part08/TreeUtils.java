package com.lihd.part08;



import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/6 13:03
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
            if(random.nextInt(4) < 3){
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


    //打印一颗二叉树
    public static void printTree(Node root){
        printNode(root,0,"\uD83C\uDF32",15);
    }

    public static void printNode(Node node, int level, String info, int width){
        if(node == null){
            //待会看看用不用打印空格
            return;
        }
        //先打印右孩子
        printNode(node.right,level + 1,"⬇︎",width);
        int numLen = getNumLen(node.value);
        int leftPaddingLen = (width - numLen - 2)/2;
        int rightPaddingLen = width - leftPaddingLen - numLen - 1;
        String leftPadding = getBlank(leftPaddingLen);
        String rightPadding = getBlank(rightPaddingLen);
        String blankPadding = getBlank(level * width);
        System.out.println(blankPadding + leftPadding + info +node.value + info+ rightPadding);
        printNode(node.left,level + 1,"⬆️",width);
    }

    public static int getNumLen(int num){
        int result = 0;
        while(num > 0){
            num /= 10;
            result ++;
        }
        return result;
    }

    public static String getBlank(int len){
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(" ");
        }
        return sb.toString();
    }

}
