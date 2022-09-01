package com.lihd.part06;

/**
 *
 * <a href="https://leetcode.com/problems/remove-boxes/submissions/">...</a>
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/29 11:59
 */
public class Code01RemoveBoxes {



    /**
     *
     * 66 ms, faster than 85.64%
     * 79.4 MB, less than 65.43%
     */
    public static int removeBoxes(int[] boxes) {

        int[][][] dp = new int[boxes.length][boxes.length][boxes.length];
        return removeBoxesMemory(0, boxes.length - 1, 0, boxes, dp);
    }
    // 下面的暴力递归改 记忆化搜索
    // 由于没有枚举行为, 并且动态规划不好改, 就不用改了
    private static int removeBoxesMemory(int l, int r, int k, int[] boxes, int[][][] dp) {
        if (l > r) {
            return 0;
        }
        if (dp[l][r][k] != 0) {
            return dp[l][r][k];
        }
        int newL = l;
        int newK = k;
        while (newL + 1 <= r && boxes[newL] == boxes[newL + 1]) {
            newL++;
            newK ++;
        }
        newL++;
        newK++;
        int ans = newK * newK + removeBoxesMemory(newL, r, 0, boxes, dp);
        for (int i = newL; i <= r; i++) {
            if (boxes[i] == boxes[l] && boxes[i - 1] != boxes[l]) {
                int val = removeBoxesMemory(newL, i - 1, 0, boxes, dp) + removeBoxesMemory(i, r, newK, boxes, dp);
                ans = Math.max(ans, val);
            }
        }
        dp[l][r][k] = ans;
        return ans;
    }


    /**
     * 这里要理解 一个很重要的概念 <br/>
     * 就是枚举的时候, 不是按一部分左边, 一部分右边枚举的.<br/>
     * 而是 将中间的作为一部分k= 0, 然后右边作为一部分并且累加上左边的包袱, 这两部分构成的.<br/>
     * @param l 开始位置
	 * @param r 结束位置
	 * @param k 前面有 k 值 和 boxes[i] 相等, 作为这个范围的包袱
	 * @param boxes 数组
     * @return int
     * @author lihd
     * @date 2022/8/30 12:43
     */
    private static int removeBoxesForce(int l, int r, int k, int[] boxes) {
        if (l > r) {
            return 0;
        }
        int newL = l;
        int newK = k;
        while (newL + 1 <= r && boxes[newL] == boxes[newL + 1]) {
            newL++;
            newK ++;
        }
        newL++;
        newK++;
        int ans = newK * newK + removeBoxesForce(newL, r, 0, boxes);
        for (int i = newL; i <= r; i++) {
            if (boxes[i] == boxes[l] && boxes[i - 1] != boxes[l]) {
                int val = removeBoxesForce(newL, i - 1, 0, boxes) +removeBoxesForce(i, r, newK, boxes);
                ans = Math.max(ans, val);
            }
        }
        return ans;
    }



    public static void main(String[] args) {
        int[] boxes = {1, 3, 2, 2, 2, 3, 4, 3, 1};
        System.out.println(removeBoxes(boxes));
    }


}
