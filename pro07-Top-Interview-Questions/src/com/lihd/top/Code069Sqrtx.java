package com.lihd.top;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/9/5 20:33
 */
public class Code069Sqrtx {

    private static final int INTEGER_MAXVALUE_SQRT = 46340;

    /**
     * 老师的做法, 纯二分, 代码比较优美<br/>
     * 1 ms, faster than 100.00%, 39.6 MB, less than 92.73%<br/>
     * @param x
     * @return int
     * @author lihd
     * @date 2022/9/8 22:39
     */
    public int mySqrt(int x) {
        int l = 0;
        int r = Math.min(INTEGER_MAXVALUE_SQRT, x);
        int m;
        int ans = 0;
        while (l <= r) {
            m = l + (r - l) / 2;
            if (m * m > x) {
                r = m - 1;
            } else {
                ans = m;
                l = m + 1;
            }
        }
        return ans;
    }

    /**
     * 难是不难, 边界讨论真恶心 <br/>
     * 老是会溢出, 所以需要很多讨论 <br/>
     * 2 ms, faster than 83.49% , 40.7 MB, less than 82.34%
     * @param x
     * @return int
     * @author lihd
     * @date 2022/9/5 20:56
     */
    public int mySqrt1(int x) {
        int l = 0;
        int r = x;
        int m = Math.min(INTEGER_MAXVALUE_SQRT, x);

        while (l <= r) {
            int pow = m * m;
            int powAddOne = (m + 1) * (m + 1);
            if (pow <= x && (powAddOne > x || powAddOne < 0)) {
                // powAddOne < 0说明溢出, 溢出说明 powAddOne的平方确实比x大
                return m;
            } else if (pow < x) {
                // pow的值小了, m 应该变大
                l = m + 1;
            } else {
                // pow的值大了, m 应该变小
                r = m - 1;
            }
            m = l + (r - l) / 2;
        }
        return -1;
    }

    public static void main(String[] args) {
        Code069Sqrtx sqrtx = new Code069Sqrtx();
        System.out.println(sqrtx.mySqrt1(2147395600));
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Math.sqrt(Integer.MAX_VALUE));
    }
}
