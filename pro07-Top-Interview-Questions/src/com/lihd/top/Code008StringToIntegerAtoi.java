package com.lihd.top;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/9/1 20:30
 */
public class Code008StringToIntegerAtoi {

    /**
     * 个人感觉 可读性比较好, 也容易理解<br>
     * 2 ms, faster than 92.08%, 41.8 MB, less than 99.26%
     * @param s 字符串
     * @return int
     * @author lihd
     * @date 2022/9/1 20:50
     */
    public int myAtoi(String s) {
        s = toValidString(s);
        if ("".equals(s)) {
            return 0;
        }
        // 记录符号位
        boolean neg = s.charAt(0) == '-';
        // 跳过符号位从1 开始
        int ans = 0;
        int lastAns = 0;
        for (int i = 1; i < s.length(); i++) {
            int num = -(s.charAt(i) - '0');
            lastAns = ans;
            ans = ans * 10 + num;
            if (ans / 10 != lastAns) {
                // 负数状态下越界正数一定越界, 这是为什么用负数
                return neg ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
        }
        // 这里一定要判断, 如果是整数, 并且 ans是整数最小值, 说明变成正数肯定越界, 先提前返回
        if(!neg && ans == Integer.MIN_VALUE){
            return Integer.MAX_VALUE;
        }
        return neg ? ans : -ans;
    }

    private String toValidString(String s) {
        char[] chs = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        boolean mustReadInteger = false;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (!mustReadInteger) {
                if (ch == '+' || ch == '-') {
                    sb.append(ch);
                    mustReadInteger = true;
                } else if (ch >= '0' && ch <= '9') {
                    sb.append("+").append(ch);
                    mustReadInteger = true;
                } else if (ch != ' ') {
                    return "";
                }
            } else {
                if (ch >= '0' & ch <= '9') {
                    sb.append(ch);
                } else {
                    break;
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Code008StringToIntegerAtoi atoi = new Code008StringToIntegerAtoi();
        String s = "2147483648";
        System.out.println(atoi.myAtoi(s));
    }


}
