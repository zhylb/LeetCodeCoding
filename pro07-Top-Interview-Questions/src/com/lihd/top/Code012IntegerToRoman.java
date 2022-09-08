package com.lihd.top;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/9/1 19:59
 */
public class Code012IntegerToRoman {

    /**
     * 数字转 罗马数字 学不到什么东西
     * 16 ms, faster than 28.39% , 42.7 MB, less than 87.12%
     * @param num 要转成罗马数字, 不超过4000
     * @return java.lang.String
     * @author lihd
     * @date 2022/9/1 20:08
     */
    public String intToRoman(int num) {
        String[][] romans = {
                {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"},
                {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"},
                {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"},
                {"", "M", "MM", "MMM"}
        };

        return romans[3][num / 1000] + romans[2][num / 100 % 10] + romans[1][num / 10 % 10] +
                romans[0][num % 10];

    }

    public static void main(String[] args) {
        Code012IntegerToRoman roman = new Code012IntegerToRoman();
        System.out.println(roman.intToRoman(2345));
    }
}
