package com.lihd.part07;


import java.util.Arrays;

/**
 * 暴力方法
 * 时间复杂度 : O(2 ^ (R * C)) 个人猜测
 * 空间复杂度 : 不知道
 * 这个复杂度虽然没有阶乘那么大, 不过2的r*c次方还是很大的
 *
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/26 16:14
 */
public class Code02PavingTile {

    public static int pavingTile(int r, int c) {
        boolean[] l = new boolean[c];
        Arrays.fill(l, true);
        return pavingTile(0, r, l);
    }

    private static int pavingTile(int row, int rowEnd, boolean[] lastDecision) {
        if (row == rowEnd) {
            return isAllTrue(lastDecision) ? 1 : 0;
        }
        // 我们可以自由发挥的地方是 上一行已经贴过瓷砖的问题
        // 如果上一行没有贴瓷砖, 那么这里我们必须竖着贴瓷砖
        boolean[] decision = reverseBooleanArr(lastDecision);
        return dfs(0, decision, row, rowEnd);
    }

    private static int dfs( int col, boolean[] decision, int row, int rowEnd) {
        if (col == decision.length) {
            //这一行 决定决策完成  去下一行决策
            return pavingTile(row + 1, rowEnd, decision);
        }
        // row行的 col 列什么也不做, 去 col + 1位置决定
        int ans = dfs(col + 1, decision, row, rowEnd);
        // row行的 col 列打算横着贴一块瓷砖, 但是需要满足一定条件
        if (col + 1 < decision.length && !decision[col] && !decision[col + 1]) {
            decision[col] = true;
            decision[col + 1] = true;
            ans += dfs(col + 2, decision, row, rowEnd);
            // 现场恢复工作
            decision[col] = false;
            decision[col + 1] = false;
        }
        return ans;
    }


    private static boolean[] reverseBooleanArr(boolean[] l) {
        boolean[] ans = new boolean[l.length];
        for (int i = 0; i < l.length; i++) {
            ans[i] = !l[i];
        }
        return ans;
    }

    private static boolean isAllTrue(boolean[] l) {
        for (boolean b : l) {
            if (!b) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // 4行 11列答案应该是 51205
        System.out.println(pavingTile(4,11));
    }

}
