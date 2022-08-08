package com.lihd.part09;

import sun.reflect.generics.tree.Tree;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 *
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/17 9:05
 */
public class Code04CommonAncestor {



    public static class Info{
        TreeNode ancestor;
        boolean hasOA;
        boolean hasOB;

        public Info(TreeNode ancestor, boolean hasOA, boolean hasOB) {
            this.ancestor = ancestor;
            this.hasOA = hasOA;
            this.hasOB = hasOB;
        }
    }

    //保证root是不空的树 OA 和 OB 在树上不为null
    public static TreeNode commonAncestor(TreeNode root, TreeNode OA, TreeNode OB) {
        return getCommonAncestorInfo(root, OA, OB).ancestor;
    }




    public static Info getCommonAncestorInfo(TreeNode node, TreeNode OA, TreeNode OB) {
        if (node == null) {
            return new Info(null, false, false);
        }
        Info leftInfo = getCommonAncestorInfo(node.left, OA, OB);
        Info rightInfo = getCommonAncestorInfo(node.right, OA, OB);

        boolean hasOA = node == OA || leftInfo.hasOA || rightInfo.hasOA;
        boolean hasOB = node == OB || leftInfo.hasOB || rightInfo.hasOB;

        TreeNode ancestor = null;

        //两个if 虽然一起写 但是至多会发生一个
        if (leftInfo.ancestor != null) {
            ancestor = leftInfo.ancestor;
        }
        if (rightInfo.ancestor != null) {
            ancestor = rightInfo.ancestor;
        }
        if (ancestor == null && hasOA && hasOB) {
            //一定要是ancestor == null时才进去 否则获取的就不是最小的公共祖先
            //情况一 node为OA 或 OB
            //情况二 node一个在左树 一个在右树
            //两种情况这么写都对
            ancestor = node;
        }
        return new Info(ancestor, hasOA, hasOB);
    }

    public static TreeNode commonAncestor2(TreeNode root, TreeNode OA, TreeNode OB) {

        HashMap<TreeNode, TreeNode> map = new HashMap<>();
        map.put(root, null);
        addToMap(root,map);

        HashSet<TreeNode> resSet = new HashSet<>();
        TreeNode cur = OA;
        while (cur != null) {
            resSet.add(cur);
            cur = map.get(cur);
        }
        cur = OB;
        while (!resSet.contains(cur)) {
            cur = map.get(cur);
        }
        return cur;
    }

    public static void addToMap(TreeNode node, Map<TreeNode, TreeNode> map) {
        //保证传入不为null
        TreeNode left = node.left;
        TreeNode right = node.right;

        if (left != null) {
            map.put(left, node);
            addToMap(left,map);
        }

        if (right != null) {
            map.put(right, node);
            addToMap(right,map);
        }
    }
}
