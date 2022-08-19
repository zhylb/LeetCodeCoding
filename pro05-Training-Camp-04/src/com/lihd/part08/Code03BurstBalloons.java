package com.lihd.part08;


/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/18 20:36
 */
public class Code03BurstBalloons {


    public static int maxScoreForce(int[] arr) {
        int[] pre = new int[arr.length + 2];
        pre[0] = 1;
        pre[pre.length - 1] = 1;
        System.arraycopy(arr, 0, pre, 1, arr.length);
        return maxScoreForce(pre, 1, arr.length);
    }

    /**
     * 这个范围两边的气球(l - 1, r + 1) 还没有破的情况下, 返回这个范围内最大的分数
     * @param arr 处理过后的气球数组
	 * @param l 开始位置
	 * @param r 结束位置
     * @return int
     * @author lihd
     * @date 2022/8/18 20:39
     */
    public static int maxScoreForce(int[] arr, int l, int r) {
        // 这是由于下面的for循环 两端造成的
        // i == l时,  调用了 f(arr, l , l - 1) 此时 l > r
        // i == r时,  调用了 f(arr, r + 1, r ) 此时 l > r
        // 这两种情况都 应该返回0, 不会影响原结果
        // 如果单独处理了边界条件, 像老师写的那样, 就不用这个判断.
        if (l > r) {
            return 0;
        }
        if (l == r) {
            return arr[l] * arr[l - 1] * arr[r + 1];
        }
        int ans = 0;
        // 枚举 l 到 r的所有位置
        for (int i = l; i <= r; i++) {
            int val = arr[i] * arr[l - 1] * arr[r + 1] + maxScoreForce(arr, l, i - 1) + maxScoreForce(arr, i + 1, r);
            ans = Math.max(val, ans);
        }
        return ans;
    }

    public static int maxCoins(int[] arr) {
        int[] pre = new int[arr.length + 2];
        pre[0] = 1;
        pre[pre.length - 1] = 1;
        System.arraycopy(arr, 0, pre, 1, arr.length);


        // 也可以写成 end = pre.length - 2;
        int end = arr.length;
        int[][] dp = new int[pre.length][pre.length];
        
        //下面的操作都是 在pre[i]中进行的

        for (int i = 1; i <= end; i++) {
            dp[i][i] = pre[i] * pre[i - 1] * pre[i + 1];
        }

        for (int l = end - 1; l >= 1; l--) {
            for (int r = l + 1; r <= end; r++) {
                // 枚举 l 到 r的所有位置
                for (int i = l; i <= r; i++) {
                    int val = pre[i] * pre[l - 1] * pre[r + 1] + dp[l][i - 1] + dp[i + 1][r];
                    dp[l][r] = Math.max(val, dp[l][r]);
                }
            }
        }
        return dp[1][end];
    }
    // 好像无法斜率有优化, 难过 用函数验证了， 果然感觉不行， 可恶
    public static int maxScoreDpSlope(int[] arr) {
        int[] pre = new int[arr.length + 2];
        pre[0] = 1;
        pre[pre.length - 1] = 1;
        System.arraycopy(arr, 0, pre, 1, arr.length);

        // 也可以写成 end = pre.length - 2;
        int end = arr.length;
        int[][] dp = new int[pre.length][pre.length];

        //下面的操作都是 在pre[i]中进行的
        for (int i = 1; i <= end; i++) {
            dp[i][i] = pre[i] * pre[i - 1] * pre[i + 1];
        }

        for (int i = 1; i <= end - 1; i++) {

            int lVal = pre[i] * pre[i - 1] * pre[i + 1] + pre[i + 1] * pre[i - 1] * pre[i + 2];
            int rVal = pre[i + 1] * pre[i] * pre[i + 2] + pre[i] * pre[i - 1] * pre[i + 2];

            dp[i][i + 1] = Math.max(lVal, rVal);
//            dp[i][i + 1] = dp[i][i] + dp[i + 1][i + 1];
        }

        for (int i = 1; i <= end - 2; i++) {
            dp[i][i + 2] = 2 * (dp[i][i + 1] + dp[i + 1][i + 2] - dp[i + 1][i + 1]);
        }

        for (int l = end - 3; l >= 1; l--) {
            for (int r = l + 3; r <= end; r++) {
                dp[l][r] = 2 * dp[l + 1][r] + 2 * dp[l][r - 1] - 3 * dp[l + 1][r - 1];
            }
        }

        return dp[1][end];
    }





    public static void main(String[] args) {
        int[] arr = { 4, 2, 3, 5, 1, 6 };
        arr = new int[]{3, 2, 5};
        int maxScoreForce = maxScoreForce(arr);
        int maxCoins = maxCoins(arr);
        int maxScoreDpSlope = maxScoreDpSlope(arr);
        System.out.println("maxScoreForce = " + maxScoreForce);
        System.out.println("maxCoins = " + maxCoins);
        System.out.println("maxScoreDpSlope = " + maxScoreDpSlope);
    }


}
