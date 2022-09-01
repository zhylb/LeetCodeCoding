package com.lihd.part02;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/24 12:36
 */
public class Code03BinaryTreeCameras {


    public static int minCameraCover(TreeNode root) {
        Info info = getInfo(root);
        return (int) Math.min(1 + info.uncovered, Math.min(info.covered, info.withCamera));
    }


    private static Info getInfo(TreeNode node) {
        if (node == null) {
            return new Info(Integer.MAX_VALUE, 0, Integer.MAX_VALUE);
        }
        Info leftInfo = getInfo(node.left);
        Info rightInfo = getInfo(node.right);
        // 按照道理不可能越界
        long uncovered = leftInfo.covered + rightInfo.covered;
        // 就算溢出 最大Integer.maxValue
        long covered = Math.min(leftInfo.withCamera + rightInfo.withCamera, Math.min(
                leftInfo.withCamera + rightInfo.covered,
                leftInfo.covered + rightInfo.withCamera
        ));
        long withCamera =1 + Math.min(leftInfo.uncovered, Math.min(leftInfo.covered, leftInfo.withCamera)) +
                Math.min(rightInfo.uncovered, Math.min(rightInfo.covered, rightInfo.withCamera));
        return new Info( uncovered,  covered,  withCamera);
    }

    private static class Info{
        // 没有被覆盖
        long uncovered;
        // 被覆盖 但是没有相机
        long covered;
        // 放置了相机
        long withCamera;

        public Info(long uncovered, long covered, long withCamera) {
            this.uncovered = uncovered;
            this.covered = covered;
            this.withCamera = withCamera;
        }
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
