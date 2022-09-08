package com.lihd.top;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/9/1 20:18
 */
public class Code007ReverseInteger {

    /**
     * 没什么说的, 两个小技巧 <br/>
     * 一个是如何防止溢出 <br/>
     * 一个是转成负数 <br/>
     * 2 ms, faster than 77.65%, 41.4 MB, less than 56.30%
     * @param x 逆序整数
     * @return int 逆序后的整数, 溢出就返回0
     * @author lihd
     * @date 2022/9/1 20:27
     */
    public int reverse(int x) {
        boolean neg = x < 0;
        // 转成负数, 因为负数范围大, 一般不会出错
        // 虽然这个题目可以用正数做, 但是用负数做一般比较好
        x = neg ? x : -x;
        int lastAns = 0;
        int ans = 0;
        while (x != 0) {
            lastAns = ans;
            ans = ans * 10 + x % 10;
            // 防止溢出 , ans * 10 / 10 != ans 说明溢出
            if (lastAns != ans / 10) {
                return 0;
            }
            x /= 10;
        }
        return neg ? ans : -ans;
    }
}
