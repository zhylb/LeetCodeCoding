package com.lihd.daily;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/9/8 12:53
 */
public class Day0908BinaryTreeInorderTraversal {

    /**
     * 为啥改成非递归还变慢了 ? 不太理解 <br/>
     * 1 ms, faster than 49.41%, 41.9 MB, less than 77.51%<br/>
     * @param root 根节点
     * @return java.util.List<java.lang.Integer>
     * @author lihd
     * @date 2022/9/8 12:57
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur  = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            list.add(cur.val);
            cur = cur.right;
        }
        return list;
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
