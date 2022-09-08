package com.lihd.top;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/9/5 13:35
 */
public class Code050PowxN {


    /**
     * 知识点 : 快速幂 , 处理Integer.MIN<br/>
     * 这个整数最小值真的很烦 <br/>
     * 1 ms, faster than 89.88%, 43.2 MB, less than 38.08%
     * @param x
	 * @param n
     * @return double
     * @author lihd
     * @date 2022/9/5 13:46
     */
    public double myPow(double x, int n) {
        if (n == 0) {
            return 1D;
        }
        int num;
        // 先把 n 转成 正数
        if (n == Integer.MIN_VALUE) {
            num = Integer.MIN_VALUE;
        } else {
            num = n < 0 ? -n : n;
        }

        // 快速幂的代码
        double ans = 1D;
        double pow = x;
        while (num != 0) {
            if ((num & 1) == 1) {
                // 说明有这一位
                ans *= pow;
            }
            num = num >>> 1;
            pow = pow * pow;
        }

        if (n == Integer.MIN_VALUE) {
            ans = ans * x;
        }
        return n > 0 ? ans : 1 / ans;
    }

    public static void main(String[] args) {
        Code050PowxN n = new Code050PowxN();
        System.out.println(n.myPow(2.000D, 10));
        System.out.println(Integer.MIN_VALUE);
    }
}
