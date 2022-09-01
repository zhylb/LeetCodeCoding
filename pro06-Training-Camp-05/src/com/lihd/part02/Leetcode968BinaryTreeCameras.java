package com.lihd.part02;

/**
 * 这个类 只是为了和 code03 那个分开, 写到一个里面难受
 *
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/24 13:13
 */
public class Leetcode968BinaryTreeCameras {
    /**
     * 1 ms, faster than 81.96%
     * 44.8 MB, less than 5.04%
     * @param root 二叉树的根节点
     * @return int
     * @author lihd
     * @date 2022/8/24 13:25
     */
    public static int minCameraCover(TreeNode root) {
        Info info = getInfo(root);
        if (info.status == Status.UNCOVERED) {
            return info.num + 1;
        }
        return info.num;
    }


    public static Info getInfo(TreeNode node){
        if (node == null) {
            return new Info(Status.COVERED, 0);
        }
        Info leftInfo = getInfo(node.left);
        Info rightInfo = getInfo(node.right);
        // 任何一个孩子没有被覆盖
        // 我们必须在这个地方放相机, 否则不能保证其孩子都是被覆盖的状态
        if (leftInfo.status == Status.UNCOVERED || rightInfo.status == Status.UNCOVERED) {
            return new Info(Status.WITH_CAMERA, leftInfo.num + rightInfo.num + 1);
        }
        // 在每个孩子被覆盖的情况下, 讨论是否有相机
        // 如果有任何一个孩子上放了相机, 当前节点就会被覆盖, 没有必要放相机
        if (leftInfo.status == Status.WITH_CAMERA || rightInfo.status == Status.WITH_CAMERA) {
            return new Info(Status.COVERED, leftInfo.num + rightInfo.num);
        }
        // 最后是每个孩子都被覆盖, 但是都 没有相机
        // 这仍不用放相机, 交给父节点抉择才能获取最优解, 只不过当前节点是未覆盖的状态.
        return new Info(Status.UNCOVERED, leftInfo.num + rightInfo.num);
    }

    private static class Info {
        Status status;
        int num;
        public Info(Status status, int num) {
            this.status = status;
            this.num = num;
        }
    }

    private static enum Status{
        UNCOVERED, COVERED, WITH_CAMERA;
    }

    // 下面的类不用粘过去, 只是为了本地编写不报错
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
