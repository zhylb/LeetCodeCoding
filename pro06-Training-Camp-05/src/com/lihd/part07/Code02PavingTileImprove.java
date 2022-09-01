package com.lihd.part07;


/**
 * 也是暴力方法, 只不过优化了常数时间
 * 时间复杂度 : O(2 ^ (R * C)) 个人猜测
 * 空间复杂度 : 不知道
 * 这个复杂度虽然没有阶乘那么大, 不过2的r*c次方还是很大的
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/26 16:47
 */
public class Code02PavingTileImprove {

    public static int pavingTile(int r, int c) {
        int lastStatus = (1 << c) - 1;
        return pavingTile(0, lastStatus, r, c);
    }

    // 如果 1 代表贴过, 0 代表没贴过
    private static int pavingTile(int row, int lastStatus, int rowEnd, int colEnd) {
        if (row == rowEnd) {
            return lastStatus == (1 << colEnd) - 1 ? 1 : 0;
        }
//        int status = ~lastStatus;
        int max = (1 << colEnd) - 1;
        System.out.println("max = " + max);
        int status = (~ lastStatus) & max;

        System.out.println(Integer.toBinaryString(status));
        return dfs(0, status, row, rowEnd, colEnd);
    }

    private static int dfs(int col, int status, int row, int rowEnd, int colEnd) {
        if (col == colEnd) {
            return pavingTile(row + 1, status, rowEnd, colEnd);
        }
        int ans = dfs(col + 1, status, row, rowEnd, colEnd);
        // 怎么取出 status 的 第 i 位
        // status的第col位是0 代表没有贴
        boolean notPaving1 = (status & (1 << col)) == 0;
        boolean notPaving2 = (status & (1 << col + 1)) == 0;
        if (col + 1 < colEnd && notPaving1 && notPaving2) {
            status |= (1 << col);
            status |= (1 << col + 1);
            ans += dfs(col + 2, status, row, rowEnd, colEnd);
            // 是整数, 不用恢复现场, 因为没有必要, 也没有用
            // status &= ~(1 << col);
            // status &= ~(1 << col + 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        // 4行 11列答案应该是 51205
        System.out.println(pavingTile(4,11));
    }

}
