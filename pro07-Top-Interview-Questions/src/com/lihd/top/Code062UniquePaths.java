package com.lihd.top;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/9/4 20:44
 */
public class Code062UniquePaths {

    /**
     * 最优解,从 m + n - 2 中选出 n - 1或者 m - 1即可 <br/>
     * 老师的做法是 每次 都把那个大结果保存,然后同时除以最大公约数, <br/>
     * 我也不知道下面的为啥一定会整除, 我记得java核心技术就是这样写的, 我就这样写了<br/>
     * 使用long是防止溢出, 不使用long太麻烦了<br/>
     * 0 ms, faster than 100.00%, 41.2 MB, less than 28.35% <br/>
     * @param m 行
	 * @param n 列
     * @return int
     * @author lihd
     * @date 2022/9/4 21:10
     */
    public int uniquePaths(int m, int n) {
        int sum = m + n - 2;
        int min = Math.min(m - 1, n - 1);
        long ans = 1;
        for (int i = 0; i < min; i++) {
            ans = ans * (sum - i) / (i + 1);
        }
        return (int) ans;
    }



    // 1 ms, faster than 53.06%
    // 40.9 MB, less than 59.38%
    public int uniquePaths2(int m, int n) {

        int[][] dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            dp[i][n - 1] = 1;
        }
        for (int i = 0; i < n; i++) {
            dp[m - 1][i] = 1;
        }

        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                dp[i][j] = dp[i + 1][j] + dp[i][j + 1];
            }
        }
        return dp[0][0];
    }

    // 超时
    public int uniquePaths1(int m, int n) {
        return move(0, 0, m, n);
    }



    private int move(int i, int j, int m, int n) {
        if (i == m - 1 || j == n - 1) {
            return 1;
        }
        return move(i, j + 1, m, n) + move(i + 1, j, m, n);
    }

    public static void main(String[] args) {
        Code062UniquePaths paths = new Code062UniquePaths();
        System.out.println(paths.uniquePaths(9,9));
        System.out.println(paths.uniquePaths2(9,9));
    }
}
