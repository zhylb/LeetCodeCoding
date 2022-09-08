package com.lihd.top;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/9/6 20:20
 */
public class Code098ValidateBinarySearchTree {

    // 老师 : 中序遍历(morris) 判断是不是升序



    // 方法一 中序遍历的结果是升序, 就是有效的二叉搜索数
    // 1 ms, faster than 58.56%, 41.9 MB, less than 91.74%
    public boolean isValidBST(TreeNode root) {
        List<Integer> list = inorderTraversal(root);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) <= list.get(i - 1)) {
                return false;
            }
        }
        return true;
    }

    private List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        dfs(root, ans);
        return ans;
    }

    private void dfs(TreeNode node, List<Integer> ans) {
        if (node != null) {
            dfs(node.left, ans);
            ans.add(node.val);
            dfs(node.right, ans);
        }
    }

    /*
     *                  ___====-_  _-====___
     *            _--^^^#####//      \\#####^^^--_
     *         _-^##########// (    ) \\##########^-_
     *        -############//  |\^^/|  \\############-
     *      _/############//   (@::@)   \\############\_
     *     /#############((     \\//     ))#############\
     *    -###############\\    (oo)    //###############-
     *   -#################\\  / VV \  //#################-
     *  -###################\\/      \//###################-
     * _#/|##########/\######(   /\   )######/\##########|\#_
     * |/ |#/\#/\#/\/  \#/\##\  |  |  /##/\#/  \/\#/\#/\#| \|
     * `  |/  V  V  `   V  \#\| |  | |/#/  V   '  V  V  \|  '
     *    `   `  `      `   / | |  | | \   '      '  '   '
     *                     (  | |  | |  )
     *                    __\ | |  | | /__
     *                   (vvv(VVV)(VVV)vvv)
     *                      Talk is cheap
     *                    Show me the code
     */
    private static final class TreeNode {
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
